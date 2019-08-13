/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thehumblefool.springbatchdemo2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;
import java.text.DateFormat;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author shash
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

//    @Autowired
//    DataSource dataSource;
    @Autowired
    EntityManagerFactory emf;

    @Bean
    public FlatFileItemReader<SalesModel> itemReader() {
        return new FlatFileItemReaderBuilder<SalesModel>()
                .name("sales_item_reader")
                .resource(new FileSystemResource("C:\\Users\\shash\\Downloads\\1500000 Sales Records\\10000 Sales Records.csv"))
                .linesToSkip(1)
                .delimited()
                .names(new String[]{"Region", "Country", "Item Type", "Sales Channel", "Order Priority", "Order Date", "Order ID", "Ship Date", "Units Sold", "Unit Price", "Unit Cost", "Total Revenue", "Total Cost", "Total Profit"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<SalesModel>() {
                    {
                        setTargetType(SalesModel.class);
                    }
                })
                .build();
    }

    @Bean
    public ItemProcessor itemProcessor() {
        return (ItemProcessor<SalesModel, SalesModel>) (final SalesModel model) -> {
//            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
//            Date parse = df.parse(model.getOrderDate());
            model.setOd(new java.sql.Date(df.parse(model.getOrderDate()).getTime()));
            model.setSd(new java.sql.Date(df.parse(model.getShipDate()).getTime()));
            model.setThread(Thread.currentThread().getName());
            return model;
        };
    }

    @Bean
    public JpaItemWriter<SalesModel> itemWriter() {
        return new JpaItemWriterBuilder<SalesModel>()
                .entityManagerFactory(emf)
                .build();
    }

//    @Bean
//    public JdbcBatchItemWriter<SalesModel> itemWriter() {
//        return new JdbcBatchItemWriterBuilder<SalesModel>()
//                .dataSource(dataSource)
//                .sql("insert into sales_records values(:id\n"
//                        + ":country\n"
//                        + ":itemType\n"
//                        + ":od\n"
//                        + ":orderID\n"
//                        + ":orderPriority\n"
//                        + ":region\n"
//                        + ":salesChannel\n"
//                        + ":sd\n"
//                        + ":thread\n"
//                        + ":totalCost\n"
//                        + ":totalProfit\n"
//                        + ":totalRevenue\n"
//                        + ":unitCost\n"
//                        + ":unitPrice\n"
//                        + ":unitsSold")
//                .beanMapped()
//                .build();
//    }
    @Bean
    public Step step1() {
        ThreadPoolTaskExecutor ts = new ThreadPoolTaskExecutor();
        ts.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        ts.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
        ts.afterPropertiesSet();

        return stepBuilderFactory.get("step1")
                .<SalesModel, SalesModel>chunk(100)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .taskExecutor(ts)
                .build();
    }

    @Bean
    public JobExecutionListenerSupport jobListener() {
        return new JobExecutionListenerSupport() {
            @Override
            public void afterJob(JobExecution jobExecution) {
                System.out.println("Job Execution ==>" + jobExecution.toString());
                System.out.println("Job Execution status ==>" + jobExecution.getStatus());
                System.out.println("Job Execution start time ==>" + jobExecution.getStartTime());
                System.out.println("Job Execution end time ==>" + jobExecution.getEndTime());
            }
        };
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("sales_records_job")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener())
                .flow(step1())
                .end()
                .build();
    }

}

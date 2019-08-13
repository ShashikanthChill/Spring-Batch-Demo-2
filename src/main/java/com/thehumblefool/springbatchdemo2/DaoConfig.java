///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.thehumblefool.springbatchdemo2;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import java.util.Properties;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import org.hibernate.cfg.Environment;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//
///**
// *
// * @author shash
// */
//@Configuration
//public class DaoConfig {
//
////    @Value(value = "ds.driver")
////    private String driverClassName;
////    @Value(value = "ds.url")
////    private String url;
////    @Value(value = "ds.username")
////    private String username;
////    @Value(value = "ds.password")
////    private String password;
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig configuration = new HikariConfig();
//        configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/test_schema");
//        configuration.setUsername("root");
//        configuration.setPassword("Shashi.742744");
//
//        return new HikariDataSource(configuration);
//    }
//
//    @Bean
//    public Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty(Environment.SHOW_SQL, "true");
//        hibernateProperties.setProperty(Environment.FORMAT_SQL, "true");
//        hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
//        hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "update");
//        return hibernateProperties;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean localSessionFactoryBean() {
//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(dataSource());
//        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
//        localSessionFactoryBean.setAnnotatedClasses(SalesModel.class);
//        return localSessionFactoryBean;
//    }
//}

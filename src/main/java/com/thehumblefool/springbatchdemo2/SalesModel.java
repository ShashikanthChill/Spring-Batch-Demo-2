/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thehumblefool.springbatchdemo2;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author shash
 */
@Entity
@Table(name = "sales_records")
public class SalesModel implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Region")
    private String region;

    @Column(name = "Country")
    private String country;

    @Column(name = "Item_Type")
    private String itemType;

    @Column(name = "Sales_Channel")
    private String salesChannel;

    @Column(name = "Order_Priority")
    private char orderPriority;

    @Transient
    private String orderDate;

    @Column(name = "Order_Date")
    private Date od;

    @Column(name = "Order_ID")
    private long orderID;

    @Transient
    private String shipDate;

    @Column(name = "Ship_Date")
    private Date sd;

    @Column(name = "Units_Sold")
    private int unitsSold;

    @Column(name = "Unit_Price")
    private double unitPrice;

    @Column(name = "Unit_Cost")
    private double unitCost;

    @Column(name = "Total_Revenue")
    private double totalRevenue;

    @Column(name = "Total_Cost")
    private double totalCost;

    @Column(name = "Total_Profit")
    private double totalProfit;

    @Column(name = "processed_thread")
    private String thread;

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public char getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(char orderPriority) {
        this.orderPriority = orderPriority;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Date getOd() {
        return od;
    }

    public void setOd(Date od) {
        this.od = od;
    }

    public Date getSd() {
        return sd;
    }

    public void setSd(Date sd) {
        this.sd = sd;
    }

}

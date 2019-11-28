/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author dhira
 */
@Entity
public class OrderHistory extends AbstractIdentifiedEntity {

    @OneToMany
    private List<Products> products = new ArrayList<>();

    private double amount;
    private String shippingAddress;
    private String paymentType;
    private LocalDate orderDate;

    public OrderHistory() {
    }

    public OrderHistory(double amount, String shippingAddress, String paymentType, LocalDate orderDate) {
        this.amount = amount;
        this.shippingAddress = shippingAddress;
        this.paymentType = paymentType;
        this.orderDate = orderDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderHistory{" + "amount=" + amount + ", shippingAddress=" + shippingAddress + ", paymentType=" + paymentType + ", orderDate=" + orderDate + '}';
    }

}

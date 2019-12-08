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
 *This orderHistory entity will be used to maintain the order history of the customers
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

    /**
     *
     */
    public OrderHistory() {
    }

    /**
     *
     * @param amount
     * @param shippingAddress
     * @param paymentType
     * @param orderDate
     */
    public OrderHistory(double amount, String shippingAddress, String paymentType, LocalDate orderDate) {
        this.amount = amount;
        this.shippingAddress = shippingAddress;
        this.paymentType = paymentType;
        this.orderDate = orderDate;
    }

    /**
     *
     * @return
     */
    public double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     *
     * @param shippingAddress
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     *
     * @return
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     *
     * @param paymentType
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     *
     * @return
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     *
     * @param orderDate
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderHistory{" + "amount=" + amount + ", shippingAddress=" + shippingAddress + ", paymentType=" + paymentType + ", orderDate=" + orderDate + '}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *The cart entity will help to maintain the cart for each customer
 * each customer will have one cart
 * @author dhira
 */
@NamedQuery(name = "cart.findByProduct", query = "select c from Cart c where c.products = :product")
@Entity
public class Cart extends AbstractIdentifiedEntity implements Serializable {

    @OneToMany
    private List<Products> products = new ArrayList<>();

    private double cartBalance;

    @OneToOne
    private SalonCustomers salonCustomers;

    /**
     *
     */
    public Cart() {
    }

    /**
     *
     * @param cartBalance
     */
    public Cart(double cartBalance) {
        this.cartBalance = cartBalance;
    }

    /**
     * Get the value of cartBalance
     *
     * @return the value of cartBalance
     */
    public double getCartBalance() {
        return cartBalance;
    }

    /**
     *
     * @param p
     */
    public void addProducts(Products p) {
        if (!this.products.contains(p)) {
            this.products.add(p);
            setCartBalance(getCartBalance() + p.getProductPrice());
            //p.setProductQuantity(p.getProductQuantity() - 1);
        }
    }

    /**
     *
     * @return
     */
    public List<Products> getProducts() {
        return products;
    }

    /**
     *
     * @param p
     */
    public void removeProducts(Products p) {
        if (!this.products.contains(p)) {
            this.products.remove(p);
        }
    }

    /**
     *
     * @return
     */
    public SalonCustomers getSalonCustomers() {
        return salonCustomers;
    }

    /**
     *
     * @param salonCustomers
     */
    public void setSalonCustomers(SalonCustomers salonCustomers) {
        this.salonCustomers = salonCustomers;
    }

    /**
     * Set the value of cartBalance
     *
     * @param cartBalance new value of cartBalance
     */
    public void setCartBalance(double cartBalance) {
        this.cartBalance = cartBalance;
    }

}

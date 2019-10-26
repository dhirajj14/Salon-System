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
import javax.persistence.OneToMany;

/**
 *
 * @author dhira
 */
@Entity
public class Cart extends AbstractIdentifiedEntity implements Serializable{

    @OneToMany
    private List <Products> products = new ArrayList<>();
    
        private double cartBalance;

    public Cart() {
    }
        
        
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
     * Set the value of cartBalance
     *
     * @param cartBalance new value of cartBalance
     */
    public void setCartBalance(double cartBalance) {
        this.cartBalance = cartBalance;
    }

 
    
    
    
}

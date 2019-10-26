/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import javax.persistence.Entity;

/**
 *
 * @author dhira
 * 
 */


@Entity
public class Products extends AbstractIdentifiedEntity{

     private String productName;
    private int productQuantity;
    private double productPrice;
    private String productSize;
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    @Override
    public String toString() {
        return "Products{" + "productName=" + productName + ", productQuantity=" + productQuantity + ", productPrice=" + productPrice + ", productSize=" + productSize + '}';
    }


    
}

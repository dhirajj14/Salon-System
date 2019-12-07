/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author dhira
 *
 */
@NamedQuery(name = "products.findAll", query = "select ps from Products ps")
@NamedQuery(name = "products.findByName", query = "select ps from Products ps where ps.productName = :productName")
@NamedQuery(name = "products.findImageById", query = "select ps from Products ps where ps.id = :productId")
@NamedQuery(name = "products.findAllBySalon", query = "select p from Products p Where p.salon = :salon")
@Entity
public class Products extends AbstractIdentifiedEntity implements Serializable {

    private String productName;
    private int productQuantity;
    private double productPrice;
    private String productSize;
    
    @Lob
    private byte[] productImage;

   
    
    @ManyToOne
    private Salon salon;


    public Products() {
    }

    public Products(String productName, int productQuantity, double productPrice, String productSize) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSize = productSize;
    }

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
     public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

     public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }
    
    
    @Override
    public String toString() {
        return "Products{" + "productName=" + productName + ", productQuantity=" + productQuantity + ", productPrice=" + productPrice + ", productSize=" + productSize + '}';
    }

}

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
import javax.validation.constraints.NotNull;

/**
 *This product entity will be used to maintain the record of the products.
 * This entity will have all the details of the products, it price, name etc.
 * @author dhira
 *
 */
@NamedQuery(name = "products.findAll", query = "select ps from Products ps")
@NamedQuery(name = "products.findByName", query = "select ps from Products ps where ps.productName = :productName")
@NamedQuery(name = "products.findImageById", query = "select ps from Products ps where ps.id = :productId")
@NamedQuery(name = "products.findAllBySalon", query = "select p from Products p Where p.salon = :salon")
@Entity
public class Products extends AbstractIdentifiedEntity implements Serializable {

    @NotNull(message = "Product Name Cannot Be Null")
    private String productName;

    @NotNull(message = "Product Quantity Cannot Be Null")
    private int productQuantity;

    @NotNull(message = "Product Price Cannot Be Null")
    private double productPrice;

    @NotNull(message = "Product Size Cannot Be Null")
    private String productSize;

    @Lob
    private byte[] productImage;

    @ManyToOne
    private Salon salon;

    /**
     *
     */
    public Products() {
    }

    /**
     *
     * @param productName
     * @param productQuantity
     * @param productPrice
     * @param productSize
     * @param productImage
     */
    public Products(String productName, int productQuantity, double productPrice, String productSize, byte[] productImage) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productImage = productImage;
    }

    /**
     *
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     *
     * @return
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     *
     * @param productQuantity
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     *
     * @return
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     *
     * @param productPrice
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     *
     * @return
     */
    public String getProductSize() {
        return productSize;
    }

    /**
     *
     * @param productSize
     */
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    /**
     *
     * @return
     */
    public Salon getSalon() {
        return salon;
    }

    /**
     *
     * @param salon
     */
    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    /**
     *
     * @return
     */
    public byte[] getProductImage() {
        return productImage;
    }

    /**
     *
     * @param productImage
     */
    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "Products{" + "productName=" + productName + ", productQuantity=" + productQuantity + ", productPrice=" + productPrice + ", productSize=" + productSize + '}';
    }

}

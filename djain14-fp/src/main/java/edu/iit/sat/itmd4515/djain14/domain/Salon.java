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
import javax.persistence.OneToOne;

/**
 *
 * @author dhira
 */
@Entity
public class Salon extends AbstractNamedEntity implements Serializable{
    @OneToOne
    private Manager manager;

    @OneToMany
    private List <Products> products = new ArrayList<>();
    
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
    
    @OneToMany(mappedBy = "salon")
    List <Employee> employees = new ArrayList<>();

    private String salonLocation;
    private String salonContact;
    public Salon() {
    }    

    public Salon(String fullName, String salonLocation, String salonContact) {
        this.fullName = fullName;
        this.salonLocation = salonLocation;
        this.salonContact = salonContact;
    }

    
    public String getSalonLocation() {
        return salonLocation;
    }

    public void setSalonLocation(String salonLocation) {
        this.salonLocation = salonLocation;
    }

    public String getSalonContact() {
        return salonContact;
    }

    public void setSalonContact(String salonContact) {
        this.salonContact = salonContact;
    }
    
    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
    
    public void addProduct(Products p){
        if(!this.products.contains(p)){
            this.products.add(p);
        }
        
    }
    
    public void removeProduct(Products a){
        if(this.products.contains(a)){
            this.products.remove(a);
        }
    }

    
    public void addemployee(Employee e){
        if(!this.employees.contains(e)){
            this.employees.add(e);
        }
         if(!e.getSalon().equals(this)){
            e.setSalon(this);
        }
        
    }
    
    public void removeEmployee(Employee e){
        if(this.employees.contains(e)){
            this.employees.remove(e);
        }
    }


    @Override
    public String toString() {
        return "Salon{" + "id=" + id + "fullName=" + fullName + ", salonLocation=" + salonLocation + ", salonContact=" + salonContact + '}';
    }
    
    
}

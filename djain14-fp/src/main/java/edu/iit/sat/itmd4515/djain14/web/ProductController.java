/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;

import edu.iit.sat.itmd4515.djain14.domain.Manager;
import edu.iit.sat.itmd4515.djain14.domain.Products;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import edu.iit.sat.itmd4515.djain14.ejbService.ManagerService;
import edu.iit.sat.itmd4515.djain14.ejbService.ProductsService;
import edu.iit.sat.itmd4515.djain14.ejbService.SalonService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sas691
 */
@Named
@RequestScoped
public class ProductController {

    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());

    private Products products;

    private Salon salon;

    private Manager manager;

    String address = "";

    @EJB
    private ProductsService productsSVC;

    @EJB
    private SalonService salonSVC;

    @EJB
    private ManagerService managerSVC;

    @Inject
    private LoginController loginController;

    private List<Products> pList = new ArrayList<>();

    public ProductController() {
    }

    @PostConstruct
    private void postContruct() {
        products = new Products();

        if (loginController.isAdmin()) {
            pList = productsSVC.findAll();
            address = "admin";
        }
        if (loginController.isManagerAdmin()) {
            manager = managerSVC.findByName(loginController.getRemoteUser());
            salon = salonSVC.findByManager(manager);
            pList = productsSVC.findAllBySalon(salon);
            address = "manager";
        }

    }

    public String prepareViewProducts(Products p) {
        this.products = p;
        LOG.info("Inside doViewProducts with " + this.products.toString());
        return "/" + address + "/viewProducts.xhtml";
    }

    public String prepareUpdateProducts(Products p) {
        this.products = p;
        LOG.info("Inside prepareUpdateProducts with " + products.toString());
        return "/" + address + "/editProducts.xhtml";
    }

    public String prepareCreateProducts() {
        this.products = new Products();
        LOG.info("Inside doCreateAdmin");
        return "/" + address + "/editProducts.xhtml";
    }

    public String prepareDeleteProducts(Products p) {
        this.products = p;
        LOG.info("Inside doDeleteProducts with " + this.products.toString());
        return "/" + address + "/deleteProducts.xhtml";
    }

    //action Methods
    public String doSaveProducts() {
        LOG.info("Inside AdminController doSaveProducts with " + this.products.toString());
        if (loginController.isAdmin()) {
            if (this.products.getId() != null) {
                LOG.info("updating on " + this.products.toString());
                productsSVC.update(products);
            } else {
                productsSVC.Create(products);
            }
        }

        if (loginController.isManagerAdmin()) {
            if (this.products.getId() != null) {
                LOG.info("updating on " + this.products.toString());
                products.setSalon(salon);
                productsSVC.update(products);
            } else {
                products.setSalon(salon);
                productsSVC.Create(products);
            }
        }

        return "/" + address + "/manageProducts.xhtml?faces-redirect=true";
    }

    public String doDeleteProducts() {
        LOG.info("Inside AdminController doDeleteEmployee with " + this.products.toString());
        productsSVC.remove(products);
        return "/" + address + "/manageProducts.xhtml?faces-redirect=true";
    }

    public List<Products> getProductsList() {
        return pList;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
}

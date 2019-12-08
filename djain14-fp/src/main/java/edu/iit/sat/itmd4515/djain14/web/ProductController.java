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
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

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

    private UploadedFile file;

    private List<Products> pList = new ArrayList<>();

    /**
     *
     */
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

    /**
     *
     * @param p
     * @return
     */
    public String prepareViewProducts(Products p) {
        this.products = p;
        LOG.info("Inside doViewProducts with " + this.products.toString());
        return "/" + address + "/viewProducts.xhtml";
    }

    /**
     *
     * @param p
     * @return
     */
    public String prepareUpdateProducts(Products p) {
        this.products = p;
        LOG.info("Inside prepareUpdateProducts with " + products.toString());
        return "/" + address + "/editProducts.xhtml";
    }

    /**
     *
     * @return
     */
    public String prepareCreateProducts() {
        this.products = new Products();
        LOG.info("Inside doCreateAdmin");
        return "/" + address + "/editProducts.xhtml";
    }

    /**
     *
     * @param p
     * @return
     */
    public String prepareDeleteProducts(Products p) {
        this.products = p;
        LOG.info("Inside doDeleteProducts with " + this.products.toString());
        return "/" + address + "/deleteProducts.xhtml";
    }

    //action Methods

    /**
     *
     * @return
     */
    public String doSaveProducts() {
        LOG.info("Inside AdminController doSaveProducts with " + this.products.toString());
        if (loginController.isAdmin()) {
            if (this.products.getId() != null) {
                LOG.info("updating on " + this.products.toString());
                upload();
                productsSVC.update(products);
            } else {
                upload();
                productsSVC.Create(products);
            }
        }

        if (loginController.isManagerAdmin()) {
            if (this.products.getId() != null) {
                LOG.info("updating on " + this.products.toString());
                products.setSalon(salon);
                upload();
                productsSVC.update(products);
            } else {
                products.setSalon(salon);
                upload();
                productsSVC.Create(products);
            }
        }

        return "/" + address + "/manageProducts.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public String doDeleteProducts() {
        LOG.info("Inside AdminController doDeleteProducts with " + this.products.toString());
        productsSVC.remove(products);
        return "/" + address + "/manageProducts.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public List<Products> getProductsList() {
        return pList;
    }

    /**
     *
     * @return
     */
    public Products getProducts() {
        return products;
    }

    /**
     *
     * @param products
     */
    public void setProducts(Products products) {
        this.products = products;
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
    public UploadedFile getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     *
     */
    public void upload() {
        byte[] content = file.getContents();
        System.out.print("Image: " + content);
        products.setProductImage(content);
    }

    /**
     *
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     *
     * @param image
     * @return
     */
    public StreamedContent getImage(byte[] image) {
        return new DefaultStreamedContent(new ByteArrayInputStream(image), "image");
    }
}

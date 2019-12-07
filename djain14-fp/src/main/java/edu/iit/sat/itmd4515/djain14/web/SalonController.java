/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;

import edu.iit.sat.itmd4515.djain14.domain.Manager;

import edu.iit.sat.itmd4515.djain14.domain.Salon;
import edu.iit.sat.itmd4515.djain14.ejbService.ManagerService;

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
public class SalonController {

    private static final Logger LOG = Logger.getLogger(SalonController.class.getName());


    private Salon salon;

    private Manager manager;

    String address = "";

  
    @EJB
    private SalonService salonSVC;

    @EJB
    private ManagerService managerSVC;

    @Inject
    private LoginController loginController;

    private List<Salon> sList = new ArrayList<>();

    public SalonController() {
    }

    @PostConstruct
    private void postContruct() {
        salon = new Salon();
        manager = new Manager();
        if (loginController.isAdmin()) {
            sList = salonSVC.findAll();
        }
       

    }

    public String prepareViewSalons(Salon s) {
        this.salon = s;
        LOG.info("Inside doViewsalons with " + this.salon.toString());
        return "/admin/viewSalon.xhtml";
    }

    public String prepareUpdateSalons(Salon s) {
        this.salon = s;
       
        this.manager = managerSVC.findByManagerName(salon.getManager().getFullName());
        manager.setSalon_flag(0);
        managerSVC.update(manager);
        LOG.info("Inside prepareUpdatesalons with " + salon.toString());
        return "/admin/editSalon.xhtml";
    }

    public String prepareCreateSalons() {
        this.salon = new Salon();
        LOG.info("Inside doCreateAdmin");
        return "/admin/editSalon.xhtml";
    }

    public String prepareDeleteSalons(Salon p) {
        this.salon = p;
        LOG.info("Inside doDeletesalons with " + this.salon.toString());
        return "/admin/deleteSalon.xhtml";
    }

    //action Methods
    public String doSaveSalon() {
        LOG.info("Inside AdminController doSavesalon with " + this.salon.toString());
        if (loginController.isAdmin()) {
            if (this.salon.getId() != null) {
                LOG.info("updating on " + this.salon.toString());
                salonSVC.update(salon);
                this.manager = managerSVC.findByManagerName(salon.getManager().getFullName());
                manager.setSalon_flag(1);
                 managerSVC.update(manager);
            } else {
                salonSVC.Create(salon);
               this.manager = managerSVC.findByManagerName(salon.getManager().getFullName());
                 manager.setSalon_flag(1);
                  managerSVC.update(manager);
            }
        }

      

        return "/admin/manageSalons.xhtml?faces-redirect=true";
    }

    public String doDeleteSalon() {
        LOG.info("Inside AdminController doDeleteEmployee with " + this.salon.toString());
        this.manager = managerSVC.findByManagerName(salon.getManager().getFullName());
        manager.setSalon_flag(0);
         managerSVC.update(manager);
        salonSVC.remove(salon);
         
        return "/admin/manageSalons.xhtml?faces-redirect=true";
    }

    public List<Salon> getsalonList() {
        return sList;
    }

    

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
}

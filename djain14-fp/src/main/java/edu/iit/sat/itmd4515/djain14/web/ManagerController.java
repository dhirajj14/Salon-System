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
 * @author dhira
 */
@Named
@RequestScoped
public class ManagerController {
    
    @EJB
    private ManagerService managerSVC;
    
    @EJB
    private SalonService salonSVC;
    
    private List <Manager> mList = new ArrayList<>();
    
    private static final Logger LOG = Logger.getLogger(ManagerController.class.getName());

    private Salon salon;
    
    private Manager manager;
    
    private String address;

    @Inject
    private LoginController loginController;
    
    public ManagerController() {
    }

    @PostConstruct
    private void postConstruct(){  
           manager = new Manager();
            mList = managerSVC.findAll();
            address = "admin";
        
    }

   public String prepareViewManager(Manager m) {
        this.manager = m;
        LOG.info("Inside doViewManager with " + this.manager.toString());
        return "/" + address + "/viewManager.xhtml";
    }

    public String prepareUpdateManager(Manager m) {
        this.manager = m;
        LOG.info("Inside doUpdateManager with " + this.manager.toString());
        return "/" + address + "/editManager.xhtml";
    }

    public String prepareCreateManager() {

        this.manager = new Manager();
        LOG.info("Inside doCreateManager with " + this.manager.toString());
        return "/" + address + "/editManager.xhtml";

    }

    public String prepareDeleteManager(Manager m) {
        this.manager = m;
        LOG.info("Inside doDeleteManager with " + this.manager.toString());
        return "/" + address + "/deleteManager.xhtml";
    }

    //action Methods
    public String doSaveManager() {
        LOG.info("Inside ManagerController doSaveManager with " + this.manager.toString());
        
        
        if (loginController.isAdmin()) {
           if (this.manager.getId() != null) {  
            LOG.info("updating on " + this.manager.toString());
            if(salonSVC.findByManager(manager) !=null){
                manager.setSalon_flag(1);
            }else{
                manager.setSalon_flag(0);
            }
            managerSVC.update(manager);
            }else{
            LOG.info("Creating " + this.toString());
            managerSVC.Create(manager);
            }
        }
        
        return "/" + address + "/manageManagers.xhtml?faces-redirect=true";
    }

    public String doDeleteManager() {
        LOG.info("Inside ManagerController doDeleteEmployee with " + this.manager.toString());

        managerSVC.remove(manager);

        return "/manager/welcome.xhtml?faces-redirect=true";
    }

     public List<Manager> getManagerList() {
            return mList;
    }
     
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
}

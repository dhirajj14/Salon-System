/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;

import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.domain.Manager;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import edu.iit.sat.itmd4515.djain14.ejbService.EmployeeService;
import edu.iit.sat.itmd4515.djain14.ejbService.ManagerService;
import edu.iit.sat.itmd4515.djain14.ejbService.SalonService;
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

public class SalonController {
    

    private Salon salon;
    
    private Manager manager;
    
    private Employee employee;
    @EJB
    private SalonService salonSVC;
    
    @EJB
    private ManagerService managerSVC;
    
    @Inject
    private LoginController loginController;
    
    @Inject
    private ManagerController managerController;
    
    private static final Logger LOG = Logger.getLogger(SalonController.class.getName());

   
    public SalonController() {
    }

    @PostConstruct
    private void postContruct() {
        salon = new Salon();
        
    }

    public String doSaveSalon(){
        LOG.info("Inside ManagerController doSaveManager with " + manager.toString() );
        managerSVC.Create(manager);
        return "welcome.xhtml";
    }
    
    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
    
     public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
    
}

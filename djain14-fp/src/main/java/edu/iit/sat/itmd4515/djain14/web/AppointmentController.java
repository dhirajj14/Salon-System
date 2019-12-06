/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;

import edu.iit.sat.itmd4515.djain14.domain.Appointment;
import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.domain.Manager;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import edu.iit.sat.itmd4515.djain14.domain.SalonCustomers;
import edu.iit.sat.itmd4515.djain14.ejbService.AppointmentService;
import edu.iit.sat.itmd4515.djain14.ejbService.EmployeeService;
import edu.iit.sat.itmd4515.djain14.ejbService.ManagerService;
import edu.iit.sat.itmd4515.djain14.ejbService.SalonCustomerService;
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
public class AppointmentController {
    

    @EJB
    private EmployeeService employeeSVC;
    
    @EJB
    private ManagerService managerSVC;
    
    @EJB
    private SalonService salonSVC;
    
    @EJB
    private SalonCustomerService salonCustomerSVC;
    

     private Employee employee;
    
    private Salon salon;
    
    private SalonCustomers salonCustomers;

    private Manager manager;
    
    private Appointment appointment;
   
    
    @EJB
    private AppointmentService appointmentSVC;
    
    private List <Appointment> aList = new ArrayList<>();
    
    
    private static final Logger LOG = Logger.getLogger(AppointmentController.class.getName());

   

   
    private String address;

    @Inject
    private LoginController loginController;
    
    public AppointmentController() {
    }

    @PostConstruct
    private void postConstruct(){  
           appointment = new Appointment();
           
          if (loginController.isAdmin()) {
            aList = appointmentSVC.findAll();
            address = "admin";
        }else if(loginController.isManagerAdmin()){
             manager = managerSVC.findByName(loginController.getRemoteUser());
             salon = salonSVC.findByManager(manager);
             aList = appointmentSVC.findAllBySalon(salon);
             address = "manager";
          }else if(loginController.isCustomer()){
              salonCustomers = salonCustomerSVC.findByName(loginController.getRemoteUser());
              aList = appointmentSVC.findAllByCustomer(salonCustomers);
              address = "customer";
          }else if(loginController.isEmployee()){
              employee = employeeSVC.findByUserName(loginController.getRemoteUser());
              aList = appointmentSVC.findAllByEmployee(employee);
              address = "employee";
          }
    }

   public String prepareViewAppointment(Appointment a) {
        this.appointment = a;
        LOG.info("Inside doViewAppointment with " + this.appointment.toString());
        return "/" + address + "/viewAppointment.xhtml";
    }

    public String prepareUpdateAppointment(Appointment a) {
        this.appointment = a;
        LOG.info("Inside doUpdateAppointment with " + this.appointment.toString());
        return "/"+ address +"/editAppointment.xhtml";
    }

    public String prepareCreateAppointment() {

        this.appointment = new Appointment();
        LOG.info("Inside doCreateAppointment with " + this.appointment.toString());
        return "/" + address + "/editAppointment.xhtml";

    }

    public String prepareDeleteAppointment(Appointment a) {
        this.appointment = a;
        LOG.info("Inside doDeleteAppointment with " + this.appointment.toString());
        return "/" + address + "/deleteAppointment.xhtml";
    }

    //action Methods
    public String doSaveAppointment() {
        LOG.info("Inside AppointmentController doSaveAppointment with " + this.appointment.toString());
        
        
        if (loginController.isAdmin()) {
           if (this.appointment.getId() != null) {
            LOG.info("updating on " + this.appointment.toString());
               
            appointmentSVC.update(appointment);
        } else {
            LOG.info("Creating " + this.toString());
            
            appointmentSVC.Create(appointment);
        }
        }
        
        if(loginController.isManagerAdmin()){

         if (this.appointment.getId() != null) {
            LOG.info("updating on " + this.appointment.toString());
            appointmentSVC.update(appointment);
        } else {
            LOG.info("Creating " + this.toString());
           appointmentSVC.Create(appointment);
        }
        }
        
        if(loginController.isCustomer()){
            if (this.appointment.getId() != null) {
            LOG.info("updating on " + this.appointment.toString());
            appointment.setSalonCustomers(salonCustomers);
            appointmentSVC.update(appointment);
        } else {
            LOG.info("Creating " + this.toString());
            appointment.setSalonCustomers(salonCustomers);
           appointmentSVC.Create(appointment);
        }
        }
        
        return "/" + address + "/welcome.xhtml?faces-redirect=true";
    }

    public String doDeleteAppointment() {
        LOG.info("Inside ManagerController doDeleteAppointmentwith " + this.appointment.toString());

        appointmentSVC.remove(appointment);

        return "/"+address+"/welcome.xhtml?faces-redirect=true";
    }

     public List<Appointment> getAppointmentList() {
        return aList;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
     public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    
     public SalonCustomers getSalonCustomers() {
        return salonCustomers;
    }

    public void setSalonCustomers(SalonCustomers salonCustomers) {
        this.salonCustomers = salonCustomers;
    }
    
    
}

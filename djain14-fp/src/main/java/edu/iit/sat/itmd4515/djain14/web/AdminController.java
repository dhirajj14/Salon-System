/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;


import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.domain.Products;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import edu.iit.sat.itmd4515.djain14.ejbService.EmployeeService;
import edu.iit.sat.itmd4515.djain14.ejbService.SalonService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author sas691
 */
@Named
@RequestScoped
public class AdminController {

    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());

    private Employee employee;
    
    private Salon salon;
    
    private Products products;
    

    @EJB
    private EmployeeService employeeSVC;
    
    @EJB
    private SalonService salonSVC;

    public AdminController() {
    }

    @PostConstruct
    private void postContruct() {
       employee = new Employee();
    }

    public String prepareViewEmployee(Employee e) {
        this.employee = e;
        LOG.info("Inside doViewEmployee with " + this.employee.toString());
        return "/admin/viewEmployee.xhtml";
    }
    
    public String prepareUpdatePet(Employee e) {
        this.employee = e;
        LOG.info("Inside prepareUpdatePet with " + employee.toString());
        return "/admin/editEmployee.xhtml";
    }

    public String prepareCreateEmployee() {
        // need to handle this special case - no param, just a new pet for this owner
        
        this.employee = new Employee();
        LOG.info("Inside doCreateAdmin");
        return "/admin/editEmployee.xhtml";
    }
    
     public String prepareDeleteEmployee(Employee e) {
        this.employee = e;
        LOG.info("Inside doDeleteEmployee with " + this.employee.toString());
        return "/admin/deleteEmployee.xhtml";
    }

    //action Methods
    public String doSaveEmployee(){
        LOG.info("Inside AdminController doSaveEmployee with " + this.employee.toString() );
         if (this.employee.getId() != null) {
            LOG.info("updating on " + this.employee.toString());
            employeeSVC.update(employee);
        } else {
            LOG.info("Creating " + this.toString());
            employeeSVC.Create(employee);
        }
         
        return "/admin/manageEmployee.xhtml?faces-redirect=true";
    }
    
    public String doDeleteEmployee() {
        LOG.info("Inside AdminController doDeleteEmployee with " + this.employee.toString());

        employeeSVC.remove(employee);

        return "/admin/manageEmployee.xhtml?faces-redirect=true";
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
    

}

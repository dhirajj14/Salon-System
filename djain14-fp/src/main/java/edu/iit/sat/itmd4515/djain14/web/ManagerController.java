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
import edu.iit.sat.itmd4515.djain14.ejbService.AppointmentService;
import edu.iit.sat.itmd4515.djain14.ejbService.EmployeeService;
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

    private Manager manager;

    private Salon salon;

    private Employee employee;

    @EJB
    private ManagerService managerSVC;

    @EJB
    private SalonService salonSVC;

    @EJB
    private EmployeeService employeeSVC;

    @EJB
    private AppointmentService appointmentSVC;

    @Inject
    private LoginController loginController;

    private List<Employee> eList = new ArrayList<>();

    private List<Appointment> aList = new ArrayList<>();

    private static final Logger LOG = Logger.getLogger(ManagerController.class.getName());

    public ManagerController() {
    }

    @PostConstruct
    private void postContruct() {
        employee = new Employee();
        manager = managerSVC.findByName(loginController.getRemoteUser());
        salon = salonSVC.findByManager(manager);
        eList = employeeSVC.findAllBySalon(salon);
    }

    public List<Employee> getEmployeeList() {
        return eList;
    }

    public String prepareViewEmployee(Employee e) {
        this.employee = e;
        LOG.info("Inside doViewEmployee with " + this.employee.toString());
        return "/manager/viewEmployee.xhtml";
    }

    public String prepareUpdateEmployee(Employee e) {
        this.employee = e;
        LOG.info("Inside doUpdateEmployee with " + this.employee.toString());
        return "/manager/editEmployee.xhtml";
    }

    public String prepareCreateEmployee() {

        this.employee = new Employee();

        LOG.info("Inside doCreateEmployee with " + this.employee.toString());
        return "/manager/editEmployee.xhtml";

    }

    public String prepareDeleteEmployee(Employee e) {
        this.employee = e;
        LOG.info("Inside doDeleteEmployee with " + this.employee.toString());
        return "/manager/deleteEmployee.xhtml";
    }

    //action Methods
    public String doSaveEmployee() {
        LOG.info("Inside ManagerController doSaveEmployee with " + this.employee.toString());
        if (this.employee.getId() != null) {
            LOG.info("updating on " + this.employee.toString());
            employee.setSalon(salon);
            employeeSVC.update(employee);
        } else {
            LOG.info("Creating " + this.toString());
            employee.setSalon(salon);
            employeeSVC.Create(employee);
        }

        return "/manager/welcome.xhtml?faces-redirect=true";
    }

    public String doDeleteEmployee() {
        LOG.info("Inside ManagerController doDeleteEmployee with " + this.employee.toString());

        employeeSVC.remove(employee);

        return "/manager/welcome.xhtml?faces-redirect=true";
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

}

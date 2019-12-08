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
public class EmployeeController {

    @EJB
    private EmployeeService employeeSVC;

    @EJB
    private ManagerService managerSVC;

    @EJB
    private SalonService salonSVC;

    private List<Employee> eList = new ArrayList<>();

    private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());

    private Employee employee;

    private Salon salon;

    private Manager manager;

    private String address;

    @Inject
    private LoginController loginController;

    /**
     *
     */
    public EmployeeController() {
    }

    @PostConstruct
    private void postConstruct() {
        employee = new Employee();

        if (loginController.isAdmin()) {
            eList = employeeSVC.findAll();
            address = "admin";
        }
        if (loginController.isManagerAdmin()) {
            manager = managerSVC.findByName(loginController.getRemoteUser());
            salon = salonSVC.findByManager(manager);
            eList = employeeSVC.findAllBySalon(salon);
            address = "manager";
        }
    }

    /**
     *
     * @param e
     * @return
     */
    public String prepareViewEmployee(Employee e) {
        this.employee = e;
        LOG.info("Inside doViewEmployee with " + this.employee.toString());
        return "/" + address + "/viewEmployee.xhtml";
    }

    /**
     *
     * @param e
     * @return
     */
    public String prepareUpdateEmployee(Employee e) {
        this.employee = e;
        LOG.info("Inside doUpdateEmployee with " + this.employee.toString());
        return "/" + address + "/editEmployee.xhtml";
    }

    /**
     *
     * @return
     */
    public String prepareCreateEmployee() {
        this.employee = new Employee();
        LOG.info("Inside doCreateEmployee with " + this.employee.toString());
        return "/" + address + "/editEmployee.xhtml";

    }

    /**
     *
     * @param e
     * @return
     */
    public String prepareDeleteEmployee(Employee e) {
        this.employee = e;
        LOG.info("Inside doDeleteEmployee with " + this.employee.toString());
        return "/" + address + "/deleteEmployee.xhtml";
    }

    //action Methods

    /**
     *
     * @return
     */
    public String doSaveEmployee() {
        LOG.info("Inside doSaveEmployee with " + this.employee.toString());

        if (loginController.isAdmin()) {
            if (this.employee.getId() != null) {
                LOG.info("updating on " + this.employee.toString());
                employeeSVC.update(employee);
            } else {
                LOG.info("Creating " + this.toString());
                employeeSVC.Create(employee);
            }
        }

        if (loginController.isManagerAdmin()) {
            if (this.employee.getId() != null) {
                LOG.info("updating on " + this.employee.toString());
                employee.setSalon(salon);
                employeeSVC.update(employee);
            } else {
                LOG.info("Creating " + this.toString());
                employee.setSalon(salon);
                employeeSVC.Create(employee);
            }
        }

        return "/" + address + "/manageEmployee.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public String doDeleteEmployee() {
        LOG.info("Inside doDeleteEmployee with " + this.employee.toString());

        employeeSVC.remove(employee);

        return "/manager/welcome.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public List<Employee> getEmployeeList() {
        return eList;
    }

    /**
     *
     * @return
     */
    public Manager getManager() {
        return manager;
    }

    /**
     *
     * @param manager
     */
    public void setManager(Manager manager) {
        this.manager = manager;
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
    public Employee getEmployee() {
        return employee;
    }

    /**
     *
     * @param employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}

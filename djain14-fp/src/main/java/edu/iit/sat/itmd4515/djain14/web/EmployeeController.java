/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;

import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.ejbService.EmployeeService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author dhira
 */
@Named
@RequestScoped
public class EmployeeController {
    

    @EJB
    private EmployeeService employeeSvc;
    
    private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());

    private Employee employee;

    public EmployeeController() {
    }

    @PostConstruct
    private void postContruct() {
        employee = new Employee();
    }

    public String doSaveAppointment(){
        LOG.info("Inside PetController doSavePet with " + employee.toString() );
        employeeSvc.Create(employee);
        return "newjsf.xhtml";
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;

import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.ejbService.EmployeeService;
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
    private EmployeeService employeeSvc;
    
    @Inject
    private SalonController salonController;
    
    private List <Employee> eList = new ArrayList<>();
    
    
    private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());

    private Employee employee;

    public EmployeeController() {
    }

    @PostConstruct
    private void postConstruct(){
              eList = employeeSvc.findAll();
              employee = new Employee();
    }

    public List<Employee> getEmployeeList() {
        return eList;
    }

    public Employee getEmployee(){
        return employee;
    }
    
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Employee;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author dhira
 */
@Stateless
public class EmployeeService extends AbstractService<Employee>{

    public EmployeeService() {
        super(Employee.class);
    }

    
    
    
    @Override
    public List<Employee> findAll() {
      return em.createNamedQuery("employee.findAll",entityClass).getResultList();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author dhira
 */
@Named
@Stateless
public class EmployeeService extends AbstractService<Employee>{

    public EmployeeService() {
        super(Employee.class);
    }

    @Override
    public List<Employee> findAll() {
      return em.createNamedQuery("employee.findAll",Employee.class).getResultList();
    }
    
    public Employee findByUserName(String userName){
        return em.createNamedQuery("employee.findByUserName",Employee.class)
                .setParameter("user", userName).getSingleResult();
    }
    
   
    public List<Employee> findAllBySalon(Salon salon) {
      return em.createNamedQuery("employee.findAllBySalon",Employee.class).setParameter("salon", salon).getResultList();
    }
    
}

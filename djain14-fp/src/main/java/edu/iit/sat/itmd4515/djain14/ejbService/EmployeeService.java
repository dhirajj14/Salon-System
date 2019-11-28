/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Appointment;
import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author dhira
 */
@Named
@Stateless
public class EmployeeService extends AbstractService<Employee> {
    
    @EJB
    private AppointmentService appointmentSVC;
    
    public EmployeeService() {
        super(Employee.class);
    }
    
    @Override
    public List<Employee> findAll() {
        return em.createNamedQuery("employee.findAll", Employee.class).getResultList();
    }
    
    public Employee findByUserName(String userName) {
        return em.createNamedQuery("employee.findByUserName", Employee.class)
                .setParameter("user", userName).getSingleResult();
    }
    
    public List<Employee> findAllBySalon(Salon salon) {
        return em.createNamedQuery("employee.findAllBySalon", Employee.class).setParameter("salon", salon).getResultList();
    }
    
    @Override
    public void update(Employee employeeFromUserForm) {
        Employee employeeFromDatabase = em.getReference(entityClass, employeeFromUserForm.getId());
        
        employeeFromDatabase.setFullName(employeeFromUserForm.getFullName());
        employeeFromDatabase.setAddress(employeeFromUserForm.getAddress());
        employeeFromDatabase.setContact(employeeFromUserForm.getContact());
        employeeFromDatabase.setEmailId(employeeFromUserForm.getEmailId());
        employeeFromDatabase.setEmployeeType(employeeFromUserForm.getEmployeeType());
        employeeFromDatabase.setSalon(employeeFromUserForm.getSalon());
        em.merge(employeeFromDatabase);
    }
    
    @Override
    public void remove(Employee employeeFromUserForm) {
        Employee employeeFromDatabase = em.getReference(entityClass, employeeFromUserForm.getId());
        List<Appointment> aList = new ArrayList<>(appointmentSVC.findByEmployee(employeeFromDatabase));
        aList.forEach((Appointment a) -> {
            a.setEmployee(null);
            appointmentSVC.remove(a);
        });
        em.remove(employeeFromDatabase);
    }
    
}

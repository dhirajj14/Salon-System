/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Appointment;
import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import edu.iit.sat.itmd4515.djain14.domain.SalonCustomers;
import java.util.List;
import javax.ejb.Stateless;

/**
 *This class maintains the CRUD operations for the appointments
 * @author dhira
 */
@Stateless
public class AppointmentService extends AbstractService<Appointment> {

    /**
     *
     */
    public AppointmentService() {
        super(Appointment.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Appointment> findAll() {
        return em.createNamedQuery("appointment.findAll", Appointment.class).getResultList();
    }

    /**
     *
     * @param e
     * @return
     */
    public List<Appointment> findByEmployee(Employee e) {
        return em.createNamedQuery("appointment.findByEmployee", Appointment.class).setParameter("employee", e).getResultList();
    }
    
    /**
     *
     * @param sc
     * @return
     */
    public List<Appointment> findAllByCustomer(SalonCustomers sc) {
        return em.createNamedQuery("appointment.findAllByCustomer", Appointment.class).setParameter("customer", sc).getResultList();
    }
     
    /**
     *
     * @param e
     * @return
     */
    public List<Appointment> findAllByEmployee(Employee e) {
        return em.createNamedQuery("appointment.findAllByEmployee", Appointment.class).setParameter("employee", e).getResultList();
    }
    
    /**
     *
     * @param s
     * @return
     */
    public List<Appointment> findAllBySalon(Salon s) {
        return em.createNamedQuery("appointment.findAllBySalon", Appointment.class).setParameter("salon", s).getResultList();
    }
     
    /**
     *
     * @param appointmentFromUserForm
     */
    @Override
    public void update(Appointment appointmentFromUserForm) {
        Appointment appointmentFromDatabase = em.getReference(entityClass, appointmentFromUserForm.getId());
        
        appointmentFromDatabase.setDate(appointmentFromUserForm.getDate());
        appointmentFromDatabase.setTime(appointmentFromUserForm.getTime());
        appointmentFromDatabase.setServiceType(appointmentFromUserForm.getServiceType());
        appointmentFromDatabase.setEmployee(appointmentFromUserForm.getEmployee());
        appointmentFromDatabase.setSalon(appointmentFromUserForm.getSalon());
        em.merge(appointmentFromDatabase);
    }

}

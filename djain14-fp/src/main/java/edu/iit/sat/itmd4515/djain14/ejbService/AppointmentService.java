/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Appointment;
import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author dhira
 */
@Stateless
public class AppointmentService extends AbstractService<Appointment> {

    public AppointmentService() {
        super(Appointment.class);
    }

    @Override
    public List<Appointment> findAll() {
        return em.createNamedQuery("appointment.findAll", Appointment.class).getResultList();
    }

    public List<Appointment> findByEmployee(Employee e) {
        return em.createNamedQuery("appointment.findByEmployee", Appointment.class).setParameter("employee", e).getResultList();
    }
    
     public List<Salon> findBySalon(Salon s) {
        return em.createNamedQuery("salon.findBySalon", Salon.class).setParameter("salon", s).getResultList();
    }

}

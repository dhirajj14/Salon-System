/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Appointment;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author dhira
 */
@Stateless
public class AppointmentService extends AbstractService<Appointment>{

    public AppointmentService() {
        super(Appointment.class);
    }

    @Override
    public List<Appointment> findAll() {
        return em.createNamedQuery("appointment.findAll", Appointment.class).getResultList();
    }
    
}

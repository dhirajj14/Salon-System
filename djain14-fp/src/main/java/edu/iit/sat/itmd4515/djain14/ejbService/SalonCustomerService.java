/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;
import edu.iit.sat.itmd4515.djain14.domain.SalonCustomers;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author dhira
 */
@Named
@Stateless
public class SalonCustomerService extends AbstractService<SalonCustomers> {

    /**
     *
     */
    public SalonCustomerService() {
        super(SalonCustomers.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<SalonCustomers> findAll() {
        return em.createNamedQuery("salonCustomers.findAll", SalonCustomers.class).getResultList();
    }

    /**
     *
     * @param name
     * @return
     */
    public SalonCustomers findByName(String name) {
        return em.createNamedQuery("salonCustomers.findByName", SalonCustomers.class).setParameter("user", name).getSingleResult();
    }
}

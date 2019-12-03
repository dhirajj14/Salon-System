/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Manager;
import edu.iit.sat.itmd4515.djain14.domain.Products;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dhira
 */
@Named
@Stateless
public class SalonService extends AbstractService<Salon> {

    public SalonService() {
        super(Salon.class);
    }

    @Override
    public List<Salon> findAll() {
        return em.createNamedQuery("salon.findAll", Salon.class).getResultList();
    }

    public Salon findByName(String fullName) {
        return em.createNamedQuery("salon.findByName", Salon.class).getSingleResult();
    }

    public Salon findByManager(Manager manager) {
        return em.createNamedQuery("salon.findByManager", Salon.class).setParameter("manager", manager).getSingleResult();
    }
    
     public Salon findByProduct(Products products) {
        return em.createNamedQuery("salon.findByProduct", Salon.class).setParameter("product", products).getSingleResult();
    }

}

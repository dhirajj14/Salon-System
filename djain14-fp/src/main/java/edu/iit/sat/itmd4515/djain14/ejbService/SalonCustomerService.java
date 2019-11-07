/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.SalonCustomers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dhira
 */
@Stateless
public class SalonCustomerService {

    @PersistenceContext(name = "itmd4515DS")
    private EntityManager em;
    
    public SalonCustomerService() {
    }
    
    public void Create(SalonCustomers sc){
        em.persist(sc);
    }
    
    public SalonCustomers find(long id){
        return em.find(SalonCustomers.class, id);
    }
    
    public List<SalonCustomers> findAll(){
        return em.createNamedQuery("salonCustomers.findAll", SalonCustomers.class).getResultList();
    }
    
    public SalonCustomers findByName(String fullName){
        return em.createNamedQuery("salonCustomers.findByName", SalonCustomers.class).getSingleResult();
    }
    
    public void update(SalonCustomers sc){
        em.merge(sc);
    }
    
    public void delete(SalonCustomers sc){
        em.remove(em.merge(sc));
    }
    
    
}

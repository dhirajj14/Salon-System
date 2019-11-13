/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

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
public class SalonService {

    @PersistenceContext(name = "itmd4515DS")
    private EntityManager em;
    
    public SalonService() {
    }
    
    public void Create(Salon s){
        em.persist(s);
    }
    
    public Salon find(long id){
        return em.find(Salon.class, id);
    }
    
    public List<Salon> findAll(){
        return em.createNamedQuery("salon.findAll", Salon.class).getResultList();
    }
    
    public Salon findByName(String fullName){
        return em.createNamedQuery("salon.findByName", Salon.class).getSingleResult();
    }
    
    public void update(Salon s){
        em.merge(s);
    }
    
    public void delete(Salon s){
        em.remove(em.merge(s));
    }
    
    
}

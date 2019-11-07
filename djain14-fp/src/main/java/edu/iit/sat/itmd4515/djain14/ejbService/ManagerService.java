/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;


import edu.iit.sat.itmd4515.djain14.domain.Manager;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dhira
 */
@Stateless
public class ManagerService {
    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;
    
    public void Create(Manager m){
        em.persist(m);
    }
    
    public Manager find(long id){
        return em.find(Manager.class, id);
    }
    
    public List<Manager> findAll(){
        return em.createNamedQuery("manager.findAll", Manager.class).getResultList();
    }
    
    public Manager findByName(String fullName){
        return em.createNamedQuery("salonCustomers.findByName", Manager.class).getSingleResult();
    }
    
    public void update(Manager m){
        em.merge(m);
    }
    
    public void delete(Manager m){
        em.remove(em.merge(m));
    }
}

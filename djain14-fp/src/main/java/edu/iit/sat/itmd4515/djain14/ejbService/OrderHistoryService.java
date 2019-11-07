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
public class OrderHistoryService{
    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;
    
    public void Create(OrderHistoryService oh){
        em.persist(oh);
    }
    
    public OrderHistoryService find(long id){
        return em.find(OrderHistoryService.class, id);
    }
    
    public List<OrderHistoryService> findAll(){
        return em.createNamedQuery("orderHistory.findAll", OrderHistoryService.class).getResultList();
    }
    
    public OrderHistoryService findByName(String fullName){
        return em.createNamedQuery("orderHistory.findByName", OrderHistoryService.class).getSingleResult();
    }
    
    public void update(OrderHistoryService oh){
        em.merge(oh);
    }
    
    public void delete(OrderHistoryService oh){
        em.remove(em.merge(oh));
    }
}

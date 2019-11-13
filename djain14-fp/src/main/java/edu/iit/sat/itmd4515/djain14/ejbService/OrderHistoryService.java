/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;


import edu.iit.sat.itmd4515.djain14.domain.Manager;
import edu.iit.sat.itmd4515.djain14.domain.OrderHistory;
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
    @PersistenceContext(name = "itmd4515DS")
    private EntityManager em;
    
    public void Create(OrderHistory oh){
        em.persist(oh);
    }
    
    public OrderHistory find(long id){
        return em.find(OrderHistory.class, id);
    }
    
    public List<OrderHistory> findAll(){
        return em.createNamedQuery("orderHistory.findAll", OrderHistory.class).getResultList();
    }
    
    public OrderHistory findByName(String fullName){
        return em.createNamedQuery("orderHistory.findByName", OrderHistory.class).getSingleResult();
    }
    
    public void update(OrderHistory oh){
        em.merge(oh);
    }
    
    public void delete(OrderHistory oh){
        em.remove(em.merge(oh));
    }
}

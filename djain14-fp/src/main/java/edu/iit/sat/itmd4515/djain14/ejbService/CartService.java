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
public class CartService {
    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;
    
    public void Create(CartService cs){
        em.persist(cs);
    }
    
    public CartService find(long id){
        return em.find(CartService.class, id);
    }
    
    public List<CartService> findAll(){
        return em.createNamedQuery("orderHistory.findAll", CartService.class).getResultList();
    }
    
    public CartService findByName(String fullName){
        return em.createNamedQuery("orderHistory.findByName", CartService.class).getSingleResult();
    }
    
    public void update(CartService cs){
        em.merge(cs);
    }
    
    public void delete(CartService cs){
        em.remove(em.merge(cs));
    }
}

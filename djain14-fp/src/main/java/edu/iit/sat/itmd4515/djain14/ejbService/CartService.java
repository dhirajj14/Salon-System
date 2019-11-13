/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;


import edu.iit.sat.itmd4515.djain14.domain.Cart;
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
    
    public void Create(Cart cs){
        em.persist(cs);
    }
    
    public Cart find(long id){
        return em.find(Cart.class, id);
    }
    
    public List<Cart> findAll(){
        return em.createNamedQuery("orderHistory.findAll", Cart.class).getResultList();
    }
    
    public Cart findByName(String fullName){
        return em.createNamedQuery("orderHistory.findByName", Cart.class).getSingleResult();
    }
    
    public void update(Cart cs){
        em.merge(cs);
    }
    
    public void delete(Cart cs){
        em.remove(em.merge(cs));
    }
}

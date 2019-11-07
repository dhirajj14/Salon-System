/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dhira
 */
@Stateless
public class ProductsService {
    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;
    
    public ProductsService() {
    }
    
    public void Create(Products ps){
        em.persist(ps);
    }
    
    public Products find(long id){
        return em.find(Products.class, id);
    }
    
    public List<Products> findAll(){
        return em.createNamedQuery("products.findAll", Products.class).getResultList();
    }
    
    public Products findByName(String fullName){
        return em.createNamedQuery("products.findByName", Products.class).getSingleResult();
    }
    
    public void update(Products ps){
        em.merge(ps);
    }
    
    public void delete(ProductsService ps){
        em.remove(em.merge(ps));
    }
}

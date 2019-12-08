/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Cart;
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
public class CartService extends AbstractService<Cart> {

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    /**
     *
     */
    public CartService() {
        super(Cart.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Cart> findAll() {
        return em.createNamedQuery("orderHistory.findAll", Cart.class).getResultList();
    }

    /**
     *
     * @param fullName
     * @return
     */
    public Cart findByName(String fullName) {
        return em.createNamedQuery("orderHistory.findByName", Cart.class).getSingleResult();
    }
    
    /**
     *
     * @param product
     * @return
     */
    public List<Cart> findByProduct(Products product) {
        return em.createNamedQuery("cart.findByProduct", Cart.class).setParameter("product", product).getResultList();
    }

}

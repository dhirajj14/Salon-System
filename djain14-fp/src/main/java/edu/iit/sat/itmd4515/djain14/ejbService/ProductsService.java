/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Cart;
import edu.iit.sat.itmd4515.djain14.domain.Products;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dhira
 */
@Stateless
@Named
public class ProductsService extends AbstractService<Products> {

    /**
     *
     */
    public ProductsService() {
        super(Products.class);
    }
    
    @EJB
    private CartService cartSVC;

    /**
     *
     * @return
     */
    @Override
    public List<Products> findAll() {
        return em.createNamedQuery("products.findAll", Products.class).getResultList();
    }

    /**
     *
     * @param fullName
     * @return
     */
    public Products findByName(String fullName) {
        return em.createNamedQuery("products.findByName", Products.class).getSingleResult();
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Products findImageById(Long id) {
        return em.createNamedQuery("products.findImageById", Products.class).setParameter("productId", id).getSingleResult();
    }
    
    /**
     *
     * @param salon
     * @return
     */
    public List<Products> findAllBySalon(Salon salon) {
        return em.createNamedQuery("products.findAllBySalon", Products.class).setParameter("salon", salon).getResultList();
    }
    
    /**
     *
     * @param productsFromUserForm
     */
    @Override
    public void update(Products productsFromUserForm) {
        Products productsFromDatabase = em.getReference(entityClass, productsFromUserForm.getId());
        productsFromDatabase.setProductName(productsFromUserForm.getProductName());
        productsFromDatabase.setProductPrice(productsFromUserForm.getProductPrice());
        productsFromDatabase.setProductQuantity(productsFromUserForm.getProductQuantity());
        productsFromDatabase.setProductSize(productsFromUserForm.getProductSize());
        productsFromDatabase.setSalon(productsFromUserForm.getSalon());
        em.merge(productsFromDatabase);
    }
    
    /**
     *
     * @param productsFromUserForm
     */
    @Override
    public void remove(Products productsFromUserForm) {
        Products productsFromDatabase = em.getReference(entityClass, productsFromUserForm.getId());
        
        List<Cart> cList = new ArrayList<>(cartSVC.findByProduct(productsFromDatabase));
        cList.forEach((Cart c) -> {
            cartSVC.remove(c);
        });
        em.remove(productsFromDatabase);
    }
    

}

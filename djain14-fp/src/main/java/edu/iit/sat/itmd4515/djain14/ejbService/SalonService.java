/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.domain.Manager;
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
@Named
@Stateless
public class SalonService extends AbstractService<Salon> {

    /**
     *
     */
    public SalonService() {
        super(Salon.class);
    }
    
    @EJB
    private ProductsService productsSVC;
    
     @EJB
    private EmployeeService employeeSVC;
    
    /**
     *
     * @return
     */
    @Override
    public List<Salon> findAll() {
        return em.createNamedQuery("salon.findAll", Salon.class).getResultList();
    }

    /**
     *
     * @param fullName
     * @return
     */
    public Salon findByName(String fullName) {
        return em.createNamedQuery("salon.findByName", Salon.class).getSingleResult();
    }

    /**
     *
     * @param manager
     * @return
     */
    public Salon findByManager(Manager manager) {
        return em.createNamedQuery("salon.findByManager", Salon.class).setParameter("manager", manager).getSingleResult();
    }
    
    /**
     *
     * @param products
     * @return
     */
    public Salon findByProduct(Products products) {
        return em.createNamedQuery("salon.findByProduct", Salon.class).setParameter("product", products).getSingleResult();
    }
     
    /**
     *
     * @param salonFromUserForm
     */
    @Override
    public void update(Salon salonFromUserForm) {
        Salon salonFromDatabase  = em.getReference(entityClass, salonFromUserForm.getId());
        salonFromDatabase.setFullName(salonFromUserForm.getFullName());
        salonFromDatabase.setSalonContact(salonFromUserForm.getSalonContact());
        salonFromDatabase.setSalonLocation(salonFromUserForm.getSalonLocation());
        salonFromDatabase.setManager(salonFromUserForm.getManager());
        em.merge(salonFromDatabase);
    }
     
    /**
     *
     * @param salonFromUserForm
     */
    @Override
    public void remove(Salon salonFromUserForm) {
        Salon salonFromDatabase = em.getReference(entityClass, salonFromUserForm.getId());
        
        List<Products> pList = new ArrayList<>(productsSVC.findAllBySalon(salonFromDatabase));
        pList.forEach((Products p) -> {
            productsSVC.remove(p);
        });
        
       List<Employee> eList = new ArrayList<>(employeeSVC.findAllBySalon(salonFromDatabase));
        eList.forEach((Employee e) -> {
            employeeSVC.remove(e);
        });
        em.remove(salonFromDatabase);
    }

}

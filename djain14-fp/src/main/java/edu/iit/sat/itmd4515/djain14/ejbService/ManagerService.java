/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.Manager;
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
public class ManagerService extends AbstractService<Manager> {

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    /**
     *
     */
    public ManagerService() {
        super(Manager.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Manager> findAll() {
        return em.createNamedQuery("manager.findAll", Manager.class).getResultList();
    }

    /**
     *
     * @param name
     * @return
     */
    public Manager findByName(String name) {
        return em.createNamedQuery("manager.findByName", Manager.class).setParameter("user", name).getSingleResult();
    }

    /**
     *
     * @param name
     * @return
     */
    public Manager findByManagerName(String name) {
        return em.createNamedQuery("manager.findByManagerName", Manager.class).setParameter("name", name).getSingleResult();
    }

    /**
     *
     * @param flag
     * @return
     */
    public List<Manager> findByFlag(int flag) {
        return em.createNamedQuery("manager.findByFlag", Manager.class).setParameter("flag", flag).getResultList();
    }

    /**
     *
     * @param managerFromUserForm
     */
    @Override
    public void update(Manager managerFromUserForm) {
        Manager managerFromDatabase = em.getReference(entityClass, managerFromUserForm.getId());
        managerFromDatabase.setFullName(managerFromUserForm.getFullName());
        managerFromDatabase.setAddress(managerFromUserForm.getAddress());
        managerFromDatabase.setContact(managerFromUserForm.getContact());
        managerFromDatabase.setEmailId(managerFromUserForm.getEmailId());
        managerFromDatabase.setSalon_flag(managerFromUserForm.getSalon_flag());
        em.merge(managerFromDatabase);
    }

}

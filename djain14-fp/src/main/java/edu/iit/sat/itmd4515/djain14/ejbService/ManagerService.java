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
public class ManagerService extends AbstractService<Manager> {

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    public ManagerService() {
        super(Manager.class);
    }

    @Override
    public List<Manager> findAll() {
        return em.createNamedQuery("manager.findAll", Manager.class).getResultList();
    }

    public Manager findByName(String name) {
        return em.createNamedQuery("manager.findByName", Manager.class).setParameter("user", name).getSingleResult();
    }

}

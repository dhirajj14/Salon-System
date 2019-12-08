/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *This is the abstract service for the CRUD operations
 * @author dhira
 * @param <T>
 */
public abstract class AbstractService<T> {

    /**
     *
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;

    /**
     *
     */
    protected final Class<T> entityClass;

    /**
     *
     * @param entityClass
     */
    public AbstractService(Class entityClass) {
        this.entityClass = entityClass;
    }

    /**
     *
     * @param entity
     */
    public void Create(T entity) {
        em.persist(entity);
    }

    /**
     *
     * @param id
     * @return
     */
    public T find(Long id) {
        return em.find(entityClass, id);
    }

    /**
     *
     * @return
     */
    public abstract List<T> findAll();

    /**
     *
     * @param entity
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     *
     * @param entity
     */
    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

}

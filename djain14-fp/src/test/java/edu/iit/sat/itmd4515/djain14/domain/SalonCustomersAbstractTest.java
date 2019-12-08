/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author dhira
 */
@TestMethodOrder(OrderAnnotation.class)
public abstract class SalonCustomersAbstractTest {

    /**
     *
     */
    protected static EntityManagerFactory emf;

    /**
     *
     */
    protected EntityManager em;

    /**
     *
     */
    protected EntityTransaction tx;

    /**
     *
     */
    public SalonCustomersAbstractTest() {
    }

    /**
     *
     */
    @BeforeAll
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    /**
     *
     */
    @AfterAll
    public static void tearDownClass() {
        emf.close();
    }

    /**
     *
     */
    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        SalonCustomers salon = new SalonCustomers("JAIN", "31st Chicago", "djain14@hawk.iit.edu", "3125369875");
        tx.begin();
        em.persist(salon);
        tx.commit();
    }

    /**
     *
     */
    @AfterEach
    public void tearDown() {
        SalonCustomers salon = em.createQuery("select sc from SalonCustomers sc where sc.fullName = :fullName", SalonCustomers.class)
                .setParameter("fullName", "JAIN")
                .getSingleResult();
        tx.begin();
        em.remove(salon);
        tx.commit();
        em.close();
    }

}

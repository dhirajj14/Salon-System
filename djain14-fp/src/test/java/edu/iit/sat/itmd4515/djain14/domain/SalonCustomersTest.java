/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author dhira
 */
@TestMethodOrder(OrderAnnotation.class)
public class SalonCustomersTest extends SalonCustomersAbstractTest{

    /**
     *
     */
    @Test
    @Order(1)
    public void createTestToBeSuccesfull() {

        SalonCustomers createSalon = new SalonCustomers("Dhiraj Jain", "31st Chicago 60616", "dhirajj75@gmail.com", "123456789");
        tx.begin();
        assertNull(createSalon.getId());
        em.persist(createSalon);
        assertNull(createSalon.getId());
        tx.commit();
        assertNotNull(createSalon.getId());
        System.out.println("\n\n\nCreated Test: " + createSalon.toString() + "\n\n");

    }

    /**
     *
     */
    @Test
    @Order(2)
    public void createTestToBeFailed() {

        SalonCustomers createSalon = new SalonCustomers("JAIN", "31st Chicago 60616", "dhirajj75@gmail.com", "123456789");
        try {
            tx.begin();
            em.persist(createSalon);
            tx.commit();

        } catch (RollbackException | InternalError e) {
            System.out.println("\n\n\nDublicate Entry: " + createSalon.toString() + "\n\n\n");
        }

    }

    /**
     *
     */
    @Test
    @Order(3)
    public void readTestToBeSuccesfull() {
     /*   SalonCustomers salon = em.createQuery("select sc from SalonCustomers sc where sc.fullName = :fullName", SalonCustomers.class)
                .setParameter("fullName", "JAIN")
                .getSingleResult();
        
        System.out.println("\n\n\nEntry Found: " + salon.toString() + "\n\n\n");
     */
     SalonCustomers salonCustomers = em.createNamedQuery("saloncustomers.findByName", SalonCustomers.class)
             .setParameter("fullName", "JAIN").getSingleResult();
        assertNotNull(salonCustomers);
        assertEquals(salonCustomers.getFullName(), "JAIN");
       
     

    }

    /**
     *
     */
    @Test
    @Order(4)
    public void readTestToBeFailed() {
        try {
            SalonCustomers salon = em.createQuery("select sc from SalonCustomers sc where sc.fullName = :fullName", SalonCustomers.class)
                    .setParameter("fullName", "Mark").getSingleResult();

        } catch (NoResultException e) {
            System.out.println("\n\n\nEntry Not Found: " + e.toString() + "\n\n\n");
        }

    }

    /**
     *
     */
    @Test
    @Order(5)
    public void updateTestToBeSuccesfyll() {
        SalonCustomers salonUpdate = em.createQuery("select su from SalonCustomers su where su.fullName = :fullName", SalonCustomers.class)
                .setParameter("fullName", "Dhiraj Jain")
                .getSingleResult();

        tx.begin();
        salonUpdate.setFullName("Dhiraj Prakash Jain");
        tx.commit();

        SalonCustomers updatedSalon = em.find(SalonCustomers.class, salonUpdate.getId());
        assertEquals(updatedSalon.getFullName(), salonUpdate.getFullName());
        System.out.println("\n\n\nEntry Found and Data was Updated" + salonUpdate.toString() + "\n\n\n");
        tx.begin();
        updatedSalon.setFullName("Dhiraj Jain");
        tx.commit();

    }

    /**
     *
     */
    @Test
    @Order(6)
    public void updateTestToBeFailed() {
        try {
            SalonCustomers salonUpdate = em.createQuery("select su from SalonCustomers su where su.fullName = :fullName", SalonCustomers.class)
                    .setParameter("fullName", "Dhiraj Prakash Jain")
                    .getSingleResult();
            assertNull(salonUpdate);
        } catch (NoResultException e) {
            System.out.println("\n\n\nNo Result found. Update cannot be done\n\n\n");
        }
    }

    /**
     *
     */
    @Test
    @Order(7)
    public void deleteTestToBeSuccesfyll() {
        SalonCustomers salon = em.createQuery("select sc from SalonCustomers sc where sc.fullName = :fullName", SalonCustomers.class)
                .setParameter("fullName", "Dhiraj Jain")
                .getSingleResult();
        tx.begin();
        em.remove(salon);
        tx.commit();

        System.out.println("\n\n\ndelete Salon Customer : " + salon.toString() + "\n\n\n");

        SalonCustomers removed = em.find(SalonCustomers.class, salon.getId());
        assertNull(removed);
    }

    /**
     *
     */
    @Test
    @Order(8)
    public void deleteTestToBeFailed() {
        try {
            SalonCustomers salonDelete = em.createQuery("select su from SalonCustomers su where su.fullName = :fullName", SalonCustomers.class)
                    .setParameter("fullName", "Dhiraj Jain")
                    .getSingleResult();
            assertNull(salonDelete);
        } catch (NoResultException e) {
            System.out.println("\n\n\nNo Result found. Operation Delete cannot execute\n\n\n");
        }
    }
    
    /**
     *
     */
    @Test
    @Order(9)
    public void salonManagerOneToOneTest() {
        Salon s = new Salon("Great Cut", "Chicago","1234567890");
        Manager m = new Manager("Dhiraj","Chicago","djain14@hawk.iit.edu","1234567809");
        s.setManager(m);
        tx.begin();
        em.persist(s);
        em.persist(m);
        tx.commit();
        
        Salon findSalon = em.find(Salon.class, s.getId());
        assertEquals(s.getFullName(), findSalon.getFullName());
        System.out.println(findSalon.toString());
        System.out.println(findSalon.getManager().getFullName());
   }
    
    /**
     *
     */
    @Test
    @Order(10)
    public void salonCustomerAppointmentOneToManyTest() {
        SalonCustomers s = new SalonCustomers("Dhiraj Jain", "31st Chicago 60616", "dhirajj75@gmail.com", "123456789");
        Appointment a  = new Appointment(LocalDate.of(2020, Month.DECEMBER, 18), ServiceType.skinCare, LocalTime.of(10,00));
        a.setSalonCustomers(s);
        s.addAppointment(a);
        tx.begin();
        em.persist(s);
        em.persist(a);
        tx.commit();
        
        Appointment findAppointment = em.find(Appointment.class, a.getId());
        assertEquals(a.getSalonCustomers(), findAppointment.getSalonCustomers());
        System.out.println(findAppointment.toString());
        System.out.println(findAppointment.getSalonCustomers().getFullName());
        
        
        tx.begin();
        s.removeAppointment(a);
        em.remove(a);
        tx.commit();
        
        
   }

}



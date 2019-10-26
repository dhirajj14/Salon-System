/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;


import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author dhira
 */
public class SalonCustomersValidationTest extends SalonCustomersValidationAbstractTest {

    public SalonCustomersValidationTest() {
    }

    @Test
    public void testFailedecauseNameIsBlankOrNull() {
        SalonCustomers badFullName = new SalonCustomers(null, "31st Chicago", "djain14@hawk.iit.edu", "3125369875");
        Set<ConstraintViolation<SalonCustomers>> constraintViolations = validator.validate(badFullName);
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be blank", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testPassBecauseNameIsBlankOrNull() {
        SalonCustomers badFullName = new SalonCustomers("Dhiraj Jain", "31st Chicago", "djain14@hawk.iit.edu", "3125369875");
        Set<ConstraintViolation<SalonCustomers>> constraintViolations = validator.validate(badFullName);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testFailedBecauseEmailIdIsBlankOrNull() {
        SalonCustomers badEmailId = new SalonCustomers("Jain", "31st Chicago", null, "3125369875");
        Set<ConstraintViolation<SalonCustomers>> constraintViolations = validator.validate(badEmailId);
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testPassBecauseEmailIdIsBlankOrNull() {
        SalonCustomers badEmailId = new SalonCustomers("Jain", "31st Chicago", "djain14@hawk.iit.edu", "3125369875");
        Set<ConstraintViolation<SalonCustomers>> constraintViolations = validator.validate(badEmailId);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testFailedBecauseContactLengthIsGreaterThanOrLessThanTen() {
        SalonCustomers badContact = new SalonCustomers("JAIN", "31st Chicago", "djain14@hawk.iit.edu", "312536987577");
        String len = badContact.getContact();
        assertTrue(len.length() != 10, "Code Length Should Be of length 3");
    }

    @Test
    public void testPassBecauseContactLengthIsGreaterThanOrLessThanTen() {
        SalonCustomers badContact = new SalonCustomers("JAIN", "31st Chicago", "djain14@hawk.iit.edu", "3125369875");
        String len = badContact.getContact();
        assertTrue(len.length() == 10, "Code Length Should Be of length 3");
    }

}

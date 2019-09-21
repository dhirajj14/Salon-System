/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14;

import edu.iit.sat.itmd4515.djain14.domain.Country;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import junit.framework.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author dhira
 */
public class CountryValidationTest {

    private static Validator validator;

    public CountryValidationTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    //Testing the code field which should not be blank or null.
    @Test
    public void testFailedBecauseCodeIsBlankOrNull() {
        Country badCountry = new Country(null,"BAD", "South Region", (float) 2.2,1);

        Set<ConstraintViolation<Country>> constraintViolations = validator.validate(badCountry);
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be blank",constraintViolations.iterator().next().getMessage());
    }
    
    //Testing the code field whose length string length should be 3.
    @Test
    public void testFailedBecauseCodeLengthIsGreaterThanThree() {
        Country badCountry = new Country("DHIR","BAD", "South Region", (float) 2.2,1);
        String len = badCountry.getCode();
        Assert.assertTrue("Code Length Should Be of length 3", len.length() != 3);
    }
    
    //Testing the Name field which should not be blank or null.
    @Test
    public void testFailedBecauseNameIsBlankOrNull() {
        Country badCountry = new Country("BAD", null, "South Region", (float) 2.2,1);

        Set<ConstraintViolation<Country>> constraintViolations = validator.validate(badCountry);
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be blank", constraintViolations.iterator().next().getMessage());
    }
    
    //Testing the Region field which should not be blank or null.
    @Test
    public void testFailureBecauseRegionIsBlankOrNull() {
        Country badCountry = new Country("BAD", "My", null, (float) 2.2,1);

        Set<ConstraintViolation<Country>> constraintViolations = validator.validate(badCountry);
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be blank", constraintViolations.iterator().next().getMessage());
    }
    
    //Testing the SurfaceArea field which should not be negative or zero.
    @Test
    public void testFailureBecauseSurfaceareaIsnegativeOrzero() {
        Country badCountry = new Country("BAD", "BAD NAME", "South Region", (float) -2.2,100);

        Set<ConstraintViolation<Country>> constraintViolations = validator.validate(badCountry);
        assertEquals(1, constraintViolations.size());
        assertEquals("Surfacearea should be positive and non-zero",constraintViolations.iterator().next().getMessage());
    }
    
    //Testing the Population field which should not be negative.
    @Test
    public void testFailureBecausePopulationIsnegative(){
        Country badCountry = new Country("BAD", "BAD NAME", "South Region", (float) 2.2, -23);

        Set<ConstraintViolation<Country>> constraintViolations = validator.validate(badCountry);
        assertEquals(1, constraintViolations.size());
        assertEquals("Population must be positive or zero",constraintViolations.iterator().next().getMessage());
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14;

import edu.iit.sat.itmd4515.djain14.domain.Country;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class CountryJDBCTest {
    private Connection connection;

    public CountryJDBCTest() {
    }

    private static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/world?zeroDateTimeBehavior=convertToNull&serverTimezone=America/Chicago";
        String username = "itmd4515";
        String password = "itmd4515";

        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    private static Country findCountry(String countryCode) throws SQLException {
        Country country = null;

        try (Connection con = getConnection();
            
                PreparedStatement ps = con.prepareStatement("select * from country where code = ?")) {
            
            ps.setString(1, countryCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                country = new Country();
                country.setCode(rs.getString("Code"));
                country.setName(rs.getString("Name"));
                country.setSurfacearea(rs.getFloat("SurfaceArea"));
                country.setPopulation(rs.getInt("Population"));
            }
        }

        return country;
    }

    
    //Inserting Dummy Entry into table before each unit testing

    private void createTestCountryBeforeEachUnitTest() throws SQLException {
        String INSERT_SQL = "INSERT into Country (Code, Name, Continent, Region, SurfaceArea, Population, LocalName, GovernmentForm, Code2) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
        ps.setString(1, "EKS");
        ps.setString(2, "EDOKS");
        ps.setString(3, "Asia");
        ps.setString(4, "Sourthern and Central Asia");
        ps.setFloat(5, Float.parseFloat("63548.23"));
        ps.setInt(6, 2100);
        ps.setString(7, "People's Country");
        ps.setString(8, "Democracy");
        ps.setString(9, "EK");
        ps.executeUpdate();
    }

    private void deleteCountry(Country country) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from country where code = ?");
        ps.setString(1, country.getCode());
        ps.executeUpdate();

    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws SQLException {
        connection = getConnection();
        createTestCountryBeforeEachUnitTest();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        Country c = new Country("EKS",null, null, (float) 22.22, null);
        deleteCountry(c);
        connection.close();
    }

    //Testing create new country function
    @Test
    public void testCreateNewCountry() throws SQLException{
        Country sas = findCountry("EKS");
        assertEquals("EKS", sas.getCode());
    }

    ///Testing retrival of country function
    @Test
    public void testReadCountry() throws SQLException{
        Country sas = findCountry("EKS");
        assertEquals("EKS", sas.getCode());
    }
    
    //Testing update country function to update the country details
    @Test
    public void testUpdateCountry() throws SQLException{
        String UPDATE_SQL = "UPDATE Country set Name = ?,Region = ?,SurfaceArea = ? WHERE code = ?"; 
        PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
        ps.setString(1, "People'ss EDOKS");
        ps.setString(2, "Central and Sourthern Asia");
        ps.setFloat(3, (float) 55555.5);
        ps.setString(4, "EKS");
        ps.executeUpdate();
        
        Country updatedCountry = findCountry("EKS");
        assertEquals("People'ss EDOKS", updatedCountry.getName());
    }
    
    // Testing delete function to delete a particular country
    @Test
    public void testDeleteCountry() throws SQLException{
         String DELETE_COUNTRY = "DELETE FROM Country WHERE code = ?"; 
        PreparedStatement ps = connection.prepareStatement(DELETE_COUNTRY);
        ps.setString(1, "EKS");
        ps.executeUpdate();
        Country delete_con= findCountry("EKS");
        assertEquals(delete_con, null);
    }
}
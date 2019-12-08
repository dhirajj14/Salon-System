/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *This entity will be used to maintain the record of the salon
 * its name, location and contact
 * @author dhira
 */
@Entity
@NamedQuery(name = "salon.findAll", query = "select s from Salon s")
@NamedQuery(name = "salon.findByManager", query = "select s from Salon s where s.manager = :manager")

public class Salon extends AbstractNamedEntity implements Serializable {

    @OneToOne
    private Manager manager;

    /**
     *
     * @return
     */
    public Manager getManager() {
        return manager;
    }

    /**
     *
     * @param manager
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salon", orphanRemoval = true)
    List<Employee> employees = new ArrayList<>();

    @NotNull(message = "Location  must not be blank")
    private String salonLocation;
    @NotNull(message = "Contact must not be blank")
    private String salonContact;

    /**
     *
     */
    public Salon() {
    }

    /**
     *
     * @param fullName
     * @param salonLocation
     * @param salonContact
     */
    public Salon(String fullName, String salonLocation, String salonContact) {
        this.fullName = fullName;
        this.salonLocation = salonLocation;
        this.salonContact = salonContact;
    }

    /**
     *
     * @return
     */
    public String getSalonLocation() {
        return salonLocation;
    }

    /**
     *
     * @param salonLocation
     */
    public void setSalonLocation(String salonLocation) {
        this.salonLocation = salonLocation;
    }

    /**
     *
     * @return
     */
    public String getSalonContact() {
        return salonContact;
    }

    /**
     *
     * @param salonContact
     */
    public void setSalonContact(String salonContact) {
        this.salonContact = salonContact;
    }

    /**
     *
     * @param e
     */
    public void addemployee(Employee e) {
        if (!this.employees.contains(e)) {
            this.employees.add(e);
        }
        if (!e.getSalon().equals(this)) {
            e.setSalon(this);
        }

    }

    /**
     *
     * @param e
     */
    public void removeEmployee(Employee e) {
        if (this.employees.contains(e)) {
            this.employees.remove(e);
        }
    }

    /**
     *
     * @return
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Salon{" + "id=" + id + "fullName=" + fullName + ", salonLocation=" + salonLocation + ", salonContact=" + salonContact + '}';
    }

}

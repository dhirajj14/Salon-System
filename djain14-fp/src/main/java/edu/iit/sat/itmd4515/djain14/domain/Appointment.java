/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dhira
 */
@NamedQuery(name = "appointment.findAll", query = "select a from Appointment a")
@NamedQuery(name = "appointment.findByEmployee", query = "select a from Appointment a where a.employee = :employee")
@NamedQuery(name = "appointment.findAllBySalon", query = "select a from Appointment a Where a.salon = :salon")
@Entity
public class Appointment extends AbstractIdentifiedEntity implements Serializable {

    @ManyToOne
    private SalonCustomers salonCustomers;
    
    @ManyToOne
    private Salon salon;


    @ManyToOne
    private Employee employee;

    @NotNull
    @FutureOrPresent
    private LocalDate apptDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @NotNull
    private LocalTime apptTime;

    public Appointment() {
    }

    public Appointment(LocalDate apptDate, ServiceType serviceType, LocalTime apptTime) {
        this.apptDate = apptDate;
        this.serviceType = serviceType;
        this.apptTime = apptTime;
    }

    public SalonCustomers getSalonCustomers() {
        return salonCustomers;
    }

    public void setSalonCustomers(SalonCustomers salonCustomers) {
        this.salonCustomers = salonCustomers;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Get the value of time
     *
     * @return the value of time
     */
    public LocalTime getTime() {
        return apptTime;
    }

    /**
     * Set the value of time
     *
     * @param apptTime new value of time
     */
    public void setTime(LocalTime apptTime) {
        this.apptTime = apptTime;
    }

    /**
     * Get the value of serviceType
     *
     * @return the value of serviceType
     */
    public ServiceType getServiceType() {
        return serviceType;
    }

    /**
     * Set the value of serviceType
     *
     * @param serviceType new value of serviceType
     */
    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public LocalDate getDate() {
        return apptDate;
    }

    /**
     * Set the value of date
     *
     * @param apptDate new value of date
     */
    public void setDate(LocalDate apptDate) {
        this.apptDate = apptDate;
    }
    
     public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @Override
    public String toString() {
        return "Appointment{" + "salonCustomers=" + salonCustomers + ", apptDate=" + apptDate + ", serviceType=" + serviceType + ", apptTime=" + apptTime + '}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author dhira
 */
public class SalonCustomersPojo {

    @NotBlank(message = "Name must not be blank")
    private String fullName;
    private String address;
    @NotBlank(message = "Email Id must not be blank")
    private String emailId;
    @NotBlank(message = "Contact must not be blank")
    private String contact;
    @FutureOrPresent(message = "Choose proper date. Date cannot be in past")
    private LocalDate date;
    @NotBlank(message = "Service must not be blank")
    private String serviceType;
    @NotBlank(message = "Time must not be blank")
    private String time;

    /**
     *
     * @param fullName
     * @param address
     * @param emailId
     * @param contact
     * @param date
     * @param serviceType
     * @param time
     */
    public SalonCustomersPojo(String fullName, String address, String emailId, String contact, LocalDate date, String serviceType, String time) {
        this.fullName = fullName;
        this.address = address;
        this.emailId = emailId;
        this.contact = contact;
        this.date = date;
        this.serviceType = serviceType;
        this.time = time;
    }

    /**
     *
     */
    public SalonCustomersPojo() {
    }

    /**
     *
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     *
     * @param emailId
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     *
     * @return
     */
    public String getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *
     * @return
     */
    public LocalDate getdate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setdate(LocalDate date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     *
     * @param serviceType
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     *
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SalonCustomers{" + "fullName=" + fullName + ", address=" + address + ", emailId=" + emailId + ", contact=" + contact + ", date=" + date + ", serviceType=" + serviceType + ", time=" + time + '}';
    }

}

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
public class SalonCustomers {

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

    public SalonCustomers(String fullName, String address, String emailId, String contact, LocalDate date, String serviceType, String time) {
        this.fullName = fullName;
        this.address = address;
        this.emailId = emailId;
        this.contact = contact;
        this.date = date;
        this.serviceType = serviceType;
        this.time = time;
    }

    public SalonCustomers() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getdate() {
        return date;
    }

    public void date(LocalDate date) {
        this.date = date;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SalonCustomers{" + "fullName=" + fullName + ", address=" + address + ", emailId=" + emailId + ", contact=" + contact + ", date=" + date + ", serviceType=" + serviceType + ", time=" + time + '}';
    }

}

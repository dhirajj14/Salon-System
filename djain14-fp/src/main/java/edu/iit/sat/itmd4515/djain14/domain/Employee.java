/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import edu.iit.sat.itmd4515.djain14.domain.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *This employee entity will be used to maintain the record of the employee.
 * This entity will have all the details of the employee
 * @author dhira
 */
@NamedQuery(name = "employee.findAll", query = "select e from Employee e")
@NamedQuery(name = "employee.findByName", query = "select e from Employee e where e.fullName = :fullName")
@NamedQuery(name = "employee.findByUserName", query = "select e from Employee e where e.user.userName = :user")
@NamedQuery(name = "employee.findAllBySalon", query = "select e from Employee e Where e.salon = :salon")
@Entity
public class Employee extends AbstractNamedEntity implements Serializable {

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToOne
    private Salon salon;

    private String address;

    @NotNull (message = "email must not be blank")
    private String emailId;

    @NotNull (message = "COntact must not be blank")
    private String contact;

    
    private EmployeeType employeeType;

    /**
     * Get the value of employeeType
     *
     * @return the value of employeeType
     */
    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    /**
     * Set the value of employeeType
     *
     * @param employeeType new value of employeeType
     */
    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    /**
     *
     * @return
     */
    public Salon getSalon() {
        return salon;
    }

    /**
     *
     * @param s
     */
    public void setSalon(Salon s) {
        this.salon = s;
    }

    /**
     *
     */
    public Employee() {
    }

    /**
     *
     * @param fullName
     * @param address
     * @param emailId
     * @param contact
     * @param employeeType
     */
    public Employee(String fullName, String address, String emailId, String contact, EmployeeType employeeType) {
        this.fullName = fullName;
        this.address = address;
        this.emailId = emailId;
        this.contact = contact;
        this.employeeType = employeeType;

    }

    /**
     * Get the value of contact
     *
     * @return the value of contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * Set the value of contact
     *
     * @param contact new value of contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Get the value of emailId
     *
     * @return the value of emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Set the value of emailId
     *
     * @param emailId new value of emailId
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fullName=" + fullName + ", address=" + address + ", emailId=" + emailId + ", contact=" + contact + ", employeeType=" + employeeType + '}';
    }

    void setType(String lizard) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Get the value of appointments
     *
     * @return the value of appointments
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Set the value of appointments
     *
     * @param appointments new value of appointments
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

}

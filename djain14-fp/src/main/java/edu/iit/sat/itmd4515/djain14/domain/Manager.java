/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import edu.iit.sat.itmd4515.djain14.domain.security.User;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dhira
 */
@NamedQuery(name = "manager.findByName", query = "select m from Manager m where m.user.userName = :user")
@Entity
public class Manager extends AbstractNamedEntity implements Serializable {

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    @OneToOne(mappedBy = "manager")
    private Salon salon;

    private String address;

    @NotNull
    private String emailId;

    @NotNull
    private String contact;

    public Manager() {
    }

    public Manager(String fullName, String address, String emailId, String contact) {
        this.fullName = fullName;
        this.address = address;
        this.emailId = emailId;
        this.contact = contact;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "Employee{" + "id=" + id + ", fullName=" + fullName + ", address=" + address + ", emailId=" + emailId + ", contact=" + contact + '}';
    }

    void setType(String lizard) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

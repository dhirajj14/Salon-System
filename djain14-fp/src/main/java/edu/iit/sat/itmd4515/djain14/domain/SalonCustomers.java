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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.jpa.config.Cascade;

/**
  *This salonCustomer entity will be used to maintain the record of the customers.
 * This entity will have all the details of the customers
 * @author dhira
 */

@NamedQuery(name = "salonCustomers.findAll", query = "select sc from SalonCustomers sc")
@NamedQuery(name = "salonCustomers.findByName", query = "select sc from SalonCustomers sc where sc.user.userName = :user")
@Entity
public class SalonCustomers extends AbstractNamedEntity implements Serializable {

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;


    @OneToMany
    private List<OrderHistory> orderHistories = new ArrayList();

    @OneToMany(mappedBy = "salonCustomers")
    //@JoinTable(joinColumns = @JoinColumn(name = "Customer_ID"), inverseJoinColumns = @JoinColumn(name = "Appt_ID"))
    private List<Appointment> appointments = new ArrayList<>();

    /**
     *
     */
    public SalonCustomers() {
    }

    private String address;

    @NotNull
    private String emailId;

    @NotNull
    private String contact;

    /**
     *
     * @param fullName
     * @param address
     * @param emailId
     * @param contact
     */
    public SalonCustomers(String fullName, String address, String emailId, String contact) {
        this.fullName = fullName;
        this.address = address;
        this.emailId = emailId;
        this.contact = contact;
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

    /**
     *
     * @return
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     *
     * @param appointments
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     *
     * @param a
     */
    public void addAppointment(Appointment a) {
        if (!this.appointments.contains(a)) {
            this.appointments.add(a);
        }
        if (!a.getSalonCustomers().equals(this)) {
            a.setSalonCustomers(this);
        }
    }

    /**
     *
     * @param a
     */
    public void removeAppointment(Appointment a) {
        if (this.appointments.contains(a)) {
            this.appointments.remove(a);
        }
        if (a.getSalonCustomers().equals(this)) {
            a.setSalonCustomers(null);
        }
    }

    /**
     *
     * @return
     */
    public List<OrderHistory> getOrderHistory() {
        return orderHistories;
    }

    /**
     *
     * @param orderHistoryies
     */
    public void setOrderHistory(List<OrderHistory> orderHistoryies) {
        this.orderHistories = orderHistoryies;
    }

    /**
     *
     * @param o
     */
    public void addOrderHistory(OrderHistory o) {
        if (!this.orderHistories.contains(o)) {
            this.orderHistories.add(o);

        }
    }

    /**
     *
     * @param o
     */
    public void removeOrderHistory(OrderHistory o) {
        if (this.orderHistories.contains(o)) {
            this.orderHistories.remove(o);
        }
    }
    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SalonCustomers{" + "id=" + id + ", fullName=" + fullName + ", address=" + address + ", emailId=" + emailId + ", contact=" + contact + '}';
    }

    void setType(String lizard) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

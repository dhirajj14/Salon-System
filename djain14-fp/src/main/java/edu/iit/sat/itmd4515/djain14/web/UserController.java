/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;


import edu.iit.sat.itmd4515.djain14.domain.security.Group;
import edu.iit.sat.itmd4515.djain14.domain.security.User;
import edu.iit.sat.itmd4515.djain14.ejbService.GroupService;
import edu.iit.sat.itmd4515.djain14.ejbService.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author sas691
 */
@Named
@RequestScoped
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    
    private User user;

    private Group group;

   
    
    @EJB
    private UserService userSVC;

    @EJB
    private GroupService groupSVC;
    
    @Inject
    private LoginController loginController;

    private List<User> uList = new ArrayList<>();

    public UserController() {
    }

    @PostConstruct
    private void postContruct() {
        user = new User();

        if (loginController.isAdmin()) {
            uList = userSVC.findAll();
        }
    }

    public String prepareViewUser(User u) {
        this.user = u;
        LOG.info("Inside doViewUser with " + this.user.toString());
        return "/admin/viewUser.xhtml";
    }

    public String prepareUpdateUser(User u) {
        this.user = u;
        LOG.info("Inside prepareUpdateUser with " + user.toString());
        return "/admin/editUser.xhtml";
    }

    public String prepareCreateUser() {
        this.user = new User();
        LOG.info("Inside doCreateUser");
        return "/admin/editUser.xhtml";
    }

    public String prepareDeleteUser(User u) {
        this.user = u;
        LOG.info("Inside doDeleteUser with " + this.user.toString());
        return "/admin/deleteUser.xhtml";
    }

    //action Methods
    public String doSaveUser() {
        LOG.info("Inside AdminController doSaveUser with " + this.user.toString());
        if (loginController.isAdmin()) {
            if (!this.user.getUserName().equals("")) {
                LOG.info("updating on " + this.user.toString());
                userSVC.update(user);
            } else {
                userSVC.Create(user);
            }
        }


        return "/admin/manageUsers.xhtml?faces-redirect=true";
    }

    public String doDeleteUser() {
        LOG.info("Inside AdminController doDeleteUser with " + this.user.toString());
        userSVC.remove(user);
        return "/admin/manageUsers.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public List<User> getUsersList() {
        return uList;
    }
    
     public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
    public List<Group> getGroupList(){
        return groupSVC.findAll();
    }

}

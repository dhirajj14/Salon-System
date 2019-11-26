/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author dhira
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @NotBlank(message = "Please enter your Username")
    private String userName;

    @NotBlank(message = "Please enter your Password")
    private String password;

    @Inject 
    private SecurityContext securityContext;
    
    @Inject 
    private FacesContext facesContext; 
    
    @Inject
    private ExternalContext externalContext;
   

    public LoginController() {
    }
    
    public String getRemoteUser(){
        return externalContext.getRemoteUser();
    }
    
    public boolean isAdmin(){
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }
    
    public boolean isEmployee(){
        return securityContext.isCallerInRole("EMPLOYEE_ROLE");
    }
    
    public boolean isCustomer(){
        return securityContext.isCallerInRole("CUSTOMER_ROLE");
    }
    
    public boolean isManagerAdmin(){
        return securityContext.isCallerInRole("MANAGER_ROLE");
    }
    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

        
        
    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String doLogin(){
        
        LOG.info("Inside doLogin");
        Credential credential = new UsernamePasswordCredential(userName, new Password(password));
        
        //LOG.info("Credential is" +credential.toString());
        
        AuthenticationStatus status = securityContext.authenticate(
                (HttpServletRequest)externalContext.getRequest(), 
                (HttpServletResponse)externalContext.getResponse(), 
                AuthenticationParameters.withParams().credential(credential));
        
        LOG.info("AuthenticationStatus is" +status.toString());
        
       switch(status){
           case NOT_DONE:
               LOG.info("case is NOT_DONE");
               return "/error.xhtml";
           case SEND_CONTINUE:
               LOG.info("case is SEND_CONTINUE");
               break;
           case SEND_FAILURE:
               LOG.info("case is SEND_FAILURE");
               return "/error.xhtml";
           case SUCCESS:
               LOG.info("case is SUCCESS");
               break;
       }
        
        return "/welcome.xhtml?faces-redirect=true";
    }
    
    public String doLogout(){
        
        try{
          ((HttpServletRequest) externalContext.getRequest()).logout();   
        }catch (ServletException ex){
            LOG.log(Level.SEVERE, null, ex);
            return "/error.xhtml";
        }
    
        return "/login.xhtml?facees-redirect=true";
    }
}

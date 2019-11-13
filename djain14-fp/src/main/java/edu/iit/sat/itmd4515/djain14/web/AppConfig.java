/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;

import edu.iit.sat.itmd4515.djain14.domain.EmployeeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author dhira
 */
@Named
@ApplicationScoped
public class AppConfig {

    public AppConfig() {
    }

    public EmployeeType[] getEmployeeType(){
        return EmployeeType.values();
}
    
}

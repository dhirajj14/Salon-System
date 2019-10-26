/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author dhira
 */
@MappedSuperclass
public class AbstractNamedEntity extends AbstractIdentifiedEntity {
    
    @NotBlank
    @Column(length = 512, nullable = false, unique = true)
    protected String fullName;

    public AbstractNamedEntity() {
    }

    /**
     * Set the value of fullName
     *
     * @param fullName new value of fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
     /**
     * Get the value of fullName
     *
     * @return the value of fullName
     */
    public String getFullName() {
        return fullName;
    }


    /**
     * Get the value of id
     *
     * @return the value of id
     */
    
}

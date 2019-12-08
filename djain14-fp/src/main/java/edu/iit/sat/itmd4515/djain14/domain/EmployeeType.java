/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

/**
 *This is the enum type class which will store the value for the type of employee
 * @author dhira
 */
public enum EmployeeType {

    /**
     *employee how does hair cut
     */
    hairCut("Hair Artist"),

    /**
     *employee who does coloring of hair
     */
    hairColor("Color Artist"),

    /**
     *skin specialist employee
     */
    skinCare("Skin Artisit");

    private String label;

    private EmployeeType(String label) {
        this.label = label;
    }

    /**
     * Get the value of label
     *
     * @return the value of label
     */
    public String getLabel() {
        return label;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

/**
 *This is an enum class to for the appointment to book the type of appointment
 * @author dhira
 */
public enum ServiceType {

    /**
     *
     */
    hairCut("Side Fade"),

    /**
     *
     */
    hairColor("Dark Brown"),

    /**
     *
     */
    skinCare("Face Scrub");

    private String label;

    private ServiceType(String label) {
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

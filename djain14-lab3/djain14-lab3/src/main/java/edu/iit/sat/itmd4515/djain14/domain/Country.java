/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author dhira
 */
public class Country {
   
    @NotBlank
    private String code;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String region;

    
    @PositiveOrZero(message = "Surfacearea should be positive and non-zero")
    private float surfacearea;
    
    @PositiveOrZero(message = "Population must be positive or zero")
    private Integer population;

    public Country() {
    }

    public Country(String code, String name, String region, float surfacearea, Integer population) {
        this.code = code;
        this.name = name;
        this.region = region;
        this.surfacearea = surfacearea;
        this.population = population;
    }

    
    

    /**
     * Get the value of code
     *
     * @return the value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the value of code
     *
     * @param code new value of code
     */
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    public float getSurfacearea() {
        return surfacearea;
    }

    public void setSurfacearea(float surfacearea) {
        this.surfacearea = surfacearea;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Country{" + "code=" + code + ", name=" + name + ", region=" + region + ", surfacearea=" + surfacearea + ", population=" + population + '}';
    }

}


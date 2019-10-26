/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.domain;


import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

/**
 *
 * @author dhira
 */
@MappedSuperclass
public class AbstractIdentifiedEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    
    @Version
    private Long version;

        private LocalDateTime createdDateTime;
        private LocalDateTime updatedDateTime;

    /**
     * Get the value of updatedDateTime
     *
     * @return the value of updatedDateTime
     */
     @PrePersist   
    public void prePersist(){
        createdDateTime = LocalDateTime.now();
    }
    
    @PreUpdate   
    public void preUpdate(){
        updatedDateTime = LocalDateTime.now();
    }
    
    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    /**
     * Set the value of updatedDateTime
     *
     * @param updatedDateTime new value of updatedDateTime
     */
    public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    /**
     * Get the value of createdDateTime
     *
     * @return the value of createdDateTime
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Set the value of createdDateTime
     *
     * @param createdDateTime new value of createdDateTime
     */
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Get the value of version
     *
     * @return the value of version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Set the value of version
     *
     * @param version new value of version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractIdentifiedEntity other = (AbstractIdentifiedEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}

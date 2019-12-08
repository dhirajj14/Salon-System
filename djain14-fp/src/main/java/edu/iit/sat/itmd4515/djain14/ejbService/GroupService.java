/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.security.Group;
import edu.iit.sat.itmd4515.djain14.domain.security.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author dhira
 */
@Stateless
@Named
public class GroupService extends AbstractService<Group> {

    /**
     *
     */
    public GroupService() {
        super(Group.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Group> findAll() {
        return em.createNamedQuery("Group.findAll", Group.class).getResultList();
    }
    
    /**
     *
     * @param user
     * @return
     */
    public List<Group> findByUser(User user){
         return em.createNamedQuery("Group.findByUser", Group.class).setParameter("user",user).getResultList();
    }

    /**
     *
     * @param name
     * @return
     */
    public Group findByName(String name){
       return em.createNamedQuery("Group.findByName", Group.class).setParameter("name",name).getSingleResult();
    }
}

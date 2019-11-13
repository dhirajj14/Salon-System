/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.security.Group;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author dhira
 */
@Stateless
public class GroupService extends AbstractService<Group>{

    public GroupService() {
        super(Group.class);
    }

    
    @Override
    public List<Group> findAll() {
        return em.createNamedQuery("Group.findAll", Group.class).getResultList();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;

import edu.iit.sat.itmd4515.djain14.domain.OrderHistory;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author dhira
 */
@Stateless
public class OrderHistoryService extends AbstractService<OrderHistory> {

    /**
     *
     */
    public OrderHistoryService() {
        super(OrderHistory.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<OrderHistory> findAll() {
        return em.createNamedQuery("orderHistory.findAll", OrderHistory.class).getResultList();
    }

    /**
     *
     * @param fullName
     * @return
     */
    public OrderHistory findByName(String fullName) {
        return em.createNamedQuery("orderHistory.findByName", OrderHistory.class).getSingleResult();
    }

}

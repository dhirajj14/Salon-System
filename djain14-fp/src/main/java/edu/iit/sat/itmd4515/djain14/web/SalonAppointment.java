/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;

import edu.iit.sat.itmd4515.djain14.domain.SalonCustomers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author dhira
 */
@WebServlet(name = "SalonAppointment", urlPatterns = {"/SalonAppt"})
public class SalonAppointment extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(SalonAppointment.class.getName());

    @Resource(lookup = "jdbc/salon")
    DataSource ds;
    
    @Resource
    Validator validator;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("In Doget");
        SalonCustomers saloncustomers = new SalonCustomers();
        request.setAttribute("saloncustomers", saloncustomers);
        try{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/newappointment.jsp");
        dispatcher.forward(request, response);
        }catch(ServletException se){
            LOG.info(se.toString());
        }catch(IOException ioe){
                        LOG.info(ioe.toString());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String emailId = request.getParameter("emailId");
        String contact = request.getParameter("contact");
        String dateParam = request.getParameter("date");
        String serviceType = request.getParameter("serviceType");
        String time = request.getParameter("time");
        LocalDate date = null;

        if (dateParam != null && !(dateParam.isEmpty())) {
            date = LocalDate.parse(dateParam);
        }
        SalonCustomers saloncustomers = new SalonCustomers(fullName, address, emailId, contact, date, serviceType, time);
       
        Set<ConstraintViolation<SalonCustomers>> constraintViolations = validator.validate(saloncustomers);

        if (constraintViolations.size() > 0) {
            LOG.info("we have a problem validating POJO");
            for (ConstraintViolation<SalonCustomers> bad : constraintViolations) {
                LOG.info(bad.getPropertyPath() + " " + bad.getMessage());
            }
            request.setAttribute("saloncustomer", saloncustomers);
            request.setAttribute("mistakes", constraintViolations);
            try{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/newappointment.jsp");
            dispatcher.forward(request, response);
            }catch(ServletException | IOException se){
                            LOG.info(se.toString());
            }
        } else {
            LOG.info("We don't have any problem with this POJO");
            
            try (Connection c = ds.getConnection()){
                 String INSERT_SQL = "INSERT into salon.customer_details (full_name, address, email, contact, service_type, date, time) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(INSERT_SQL);
        ps.setString(1,saloncustomers.getFullName() );
        ps.setString(2, saloncustomers.getAddress());
        ps.setString(3, saloncustomers.getEmailId());
        ps.setString(4, saloncustomers.getContact());
        ps.setString(5, saloncustomers.getServiceType());
        ps.setString(6, saloncustomers.getdate().toString());
        ps.setString(7,saloncustomers.getTime());
        ps.execute();
        LOG.info(ps.toString());
            } catch (Exception e) {
            }
            request.setAttribute("saloncustomer", saloncustomers);
            try{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/appointmentconfirm.jsp");
            dispatcher.forward(request, response);
            }catch(ServletException | IOException se){
                            LOG.info(se.toString());
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

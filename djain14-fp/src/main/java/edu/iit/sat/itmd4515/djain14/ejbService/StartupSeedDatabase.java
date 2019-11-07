/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;
import edu.iit.sat.itmd4515.djain14.domain.Appointment;
import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.domain.EmployeeType;
import edu.iit.sat.itmd4515.djain14.domain.Products;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import edu.iit.sat.itmd4515.djain14.domain.SalonCustomers;
import edu.iit.sat.itmd4515.djain14.domain.ServiceType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dhira
 */
@Startup
@Singleton
public class StartupSeedDatabase {
    
    private static final Logger LOG = Logger.getLogger(StartupSeedDatabase.class.getName());
    
    @PersistenceContext(name = "itmd4515DS")
    EntityManager em;

    @EJB
    EmployeeService employeeSvc;
    
    @EJB
    AppointmentService appointmentSvc;
    
    
    @EJB
    SalonCustomerService salonCustomerSvc;
    
    @EJB
    SalonService salonSvc;
    
     @EJB
    ProductsService productSvc;
    
    public StartupSeedDatabase() {
    }
    
    @PostConstruct
    private void seedDatabase(){
        LOG.info("StartupSeedDatabase");
        SalonCustomers sc1 = new SalonCustomers("Customer 1", "31st Chicago 60616", "dhirajj75@gmail.com", "123456789");
        Appointment a1  = new Appointment(LocalDate.of(2019, Month.NOVEMBER, 20), ServiceType.hairCut, LocalTime.of(10, 30));
        Appointment a2  = new Appointment(LocalDate.of(2019, Month.NOVEMBER, 25), ServiceType.hairColor, LocalTime.of(12, 30));
        Employee e1 = new Employee("Employee 1", "31st Chicago 60616", "e1@gmail.com", "123456789", EmployeeType.hairCut);
        Employee e2 = new Employee("Employee 2", "31st Chicago 60616", "e2@gmail.com", "123456789", EmployeeType.hairColor);
        Products ps1 = new Products("Hair Cream", 4, 50, "50gms");
        Salon s1 = new Salon("One Cut", "Chicago","1234567890");
        
        a1.setSalonCustomers(sc1);
        a2.setSalonCustomers(sc1);
        sc1.addAppointment(a1);
        sc1.addAppointment(a2);
        a1.setEmployee(e1);
        a2.setEmployee(e2);
        
        
        salonCustomerSvc.Create(sc1);
        employeeSvc.Create(e1);
        employeeSvc.Create(e2);
        appointmentSvc.Create(a1);
        appointmentSvc.Create(a2);
         productSvc.Create(ps1);
        salonSvc.Create(s1);
        

        LOG.info("\nappointments :");
        for(Appointment a : appointmentSvc.findAll()){
            LOG.info("\n\nAppointment: "+a.toString());
        }
        
        
         LOG.info("\nEmployees :");
        for(Employee e : employeeSvc.findAll()){
            LOG.info(e.toString());
        }
        
  
        s1.addProduct(ps1);
        e1.setSalon(s1);
        e2.setSalon(s1);
        s1.addemployee(e1);
        s1.addemployee(e1);
        
       
        
    }
}

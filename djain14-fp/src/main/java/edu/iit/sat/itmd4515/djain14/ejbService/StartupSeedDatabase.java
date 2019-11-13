/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.ejbService;
import edu.iit.sat.itmd4515.djain14.domain.Appointment;
import edu.iit.sat.itmd4515.djain14.domain.Cart;
import edu.iit.sat.itmd4515.djain14.domain.Employee;
import edu.iit.sat.itmd4515.djain14.domain.EmployeeType;
import edu.iit.sat.itmd4515.djain14.domain.OrderHistory;
import edu.iit.sat.itmd4515.djain14.domain.Products;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import edu.iit.sat.itmd4515.djain14.domain.SalonCustomers;
import edu.iit.sat.itmd4515.djain14.domain.ServiceType;
import edu.iit.sat.itmd4515.djain14.domain.security.Group;
import edu.iit.sat.itmd4515.djain14.domain.security.User;
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
    
    @PersistenceContext(name = "itmd4515PU")
    EntityManager em;

    @EJB
    EmployeeService employeeSvc;
    
    @EJB
    OrderHistoryService orderSvc;
    
    @EJB
    CartService cartSvs;
    
    @EJB
    AppointmentService appointmentSvc;
    
    
    @EJB
    SalonCustomerService salonCustomerSvc;
    
    @EJB
    SalonService salonSvc;
    
    @EJB
    ProductsService productSvc;
    
    @EJB
    UserService userSvc;
    
    @EJB
    GroupService groupSvc;
    
    public StartupSeedDatabase() {
    }
    
    @PostConstruct
    private void seedDatabase(){
        LOG.info("StartupSeedDatabase");
        
        User admin = new User("admin","admin", Boolean.TRUE);
        Group adminGroup = new Group("ADMIN_GROUP", "This group holds admins in this mock identity store");
        admin.addGroup(adminGroup);
        
        groupSvc.Create(adminGroup);
        userSvc.Create(admin);
        
        Group employeeGroup = new Group("EMPLOYEE_GROUP", "This group holds employees in this mock identity store");
        Group customerGroup = new Group("CUSTOMER_GROUP", "This group holds admins in this mock identity store");
        
        groupSvc.Create(employeeGroup);
        groupSvc.Create(customerGroup);
        
        User employee1 = new User("Employee1", "Employee1", Boolean.TRUE);
        employee1.addGroup(employeeGroup);
        employee1.addGroup(adminGroup);
        User employee2 = new User("Employee2", "Employee2", Boolean.TRUE);
        employee2.addGroup(employeeGroup);
        User customer1 = new User("customer1", "customer1", Boolean.TRUE);
        customer1.addGroup(customerGroup);
        User customer2 = new User("customer2", "customer2", Boolean.TRUE);
        customer2.addGroup(customerGroup);
        
        userSvc.Create(employee1);
        userSvc.Create(employee2);
        userSvc.Create(customer1);
        userSvc.Create(customer2);
        
        
        
        
        
        
        
         
        SalonCustomers sc1 = new SalonCustomers("Customer 1", "31st Chicago 60616", "dhirajj75@gmail.com", "123456789");
        sc1.setUser(customer1);
        SalonCustomers sc2 = new SalonCustomers("Customer 2", "31st Chicago 60616", "d@gmail.com", "123456789");
        sc1.setUser(customer2);
        Appointment a1  = new Appointment(LocalDate.of(2019, Month.NOVEMBER, 20), ServiceType.hairCut, LocalTime.of(10, 30));
        Appointment a2  = new Appointment(LocalDate.of(2019, Month.NOVEMBER, 25), ServiceType.hairColor, LocalTime.of(12, 30));
        Employee e1 = new Employee("Employee 1", "31st Chicago 60616", "e1@gmail.com", "123456789", EmployeeType.hairCut);
        e1.setUser(employee1);
        Employee e2 = new Employee("Employee 2", "31st Chicago 60616", "e2@gmail.com", "123456789", EmployeeType.hairColor);
        e2.setUser(employee2);
        Products ps1 = new Products("Hair Cream", 4, 50, "50gms");
        Salon s1 = new Salon("One Cut", "Chicago","1234567890");
        Cart c1 = new Cart(0);
        
        
        a1.setSalonCustomers(sc1);
        a2.setSalonCustomers(sc1);
        sc1.addAppointment(a1);
        sc1.addAppointment(a2);
        a1.setEmployee(e1);
        a2.setEmployee(e2);
        sc1.setCart(c1);
        s1.addProduct(ps1);
        e1.setSalon(s1);
        e2.setSalon(s1);
        s1.addemployee(e1);
        s1.addemployee(e1);
        c1.addProducts(ps1);
        c1.addProducts(ps1);
        
        OrderHistory o = new OrderHistory(c1.getCartBalance(),"400 E 33RD Street","Card",LocalDate.now());
        sc1.addOrderHistory(o);
      
         
        cartSvs.Create(c1);
        orderSvc.Create(o);
        salonCustomerSvc.Create(sc1);
        salonCustomerSvc.Create(sc2);
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
    }
}

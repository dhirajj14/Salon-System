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
import edu.iit.sat.itmd4515.djain14.domain.Manager;
import edu.iit.sat.itmd4515.djain14.domain.OrderHistory;
import edu.iit.sat.itmd4515.djain14.domain.Products;
import edu.iit.sat.itmd4515.djain14.domain.Salon;
import edu.iit.sat.itmd4515.djain14.domain.SalonCustomers;
import edu.iit.sat.itmd4515.djain14.domain.ServiceType;
import edu.iit.sat.itmd4515.djain14.domain.security.Group;
import edu.iit.sat.itmd4515.djain14.domain.security.User;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    
    private byte[] image;

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
    ManagerService managerSvc;

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

    /**
     *
     */
    public StartupSeedDatabase() {
    }

    @PostConstruct
    private void seedDatabase() {
        LOG.info("StartupSeedDatabase");

        User admin = new User("Admin", "Admin", Boolean.TRUE);
        Group adminGroup = new Group("ADMIN_GROUP", "This group holds admins in this mock identity store");
        Group managerGroup = new Group("MANAGER_GROUP", "This group holds Manager admins in this mock identity store");
        admin.addGroup(adminGroup);

        Group employeeGroup = new Group("EMPLOYEE_GROUP", "This group holds employees in this mock identity store");
        Group customerGroup = new Group("CUSTOMER_GROUP", "This group holds admins in this mock identity store");

        groupSvc.Create(adminGroup);
        groupSvc.Create(employeeGroup);
        groupSvc.Create(customerGroup);
        groupSvc.Create(managerGroup);
        userSvc.Create(admin);

        User employee1 = new User("Employee1", "Employee1", Boolean.TRUE);
        employee1.addGroup(employeeGroup);
        employee1.addGroup(adminGroup);
        User employee2 = new User("Employee2", "Employee2", Boolean.TRUE);
        employee2.addGroup(employeeGroup);
        User employee3 = new User("Employee3", "Employee3", Boolean.TRUE);
        employee3.addGroup(employeeGroup);
        User customer1 = new User("Customer1", "Customer1", Boolean.TRUE);
        customer1.addGroup(customerGroup);
        User customer2 = new User("Customer2", "Customer2", Boolean.TRUE);
        customer2.addGroup(customerGroup);
        User manager1 = new User("Manager1", "Manager1", Boolean.TRUE);
        User manager2 = new User("Manager2", "Manager2", Boolean.TRUE);

        userSvc.Create(manager1);
        userSvc.Create(manager2);
        userSvc.Create(employee1);
        userSvc.Create(employee2);
        userSvc.Create(employee3);
        userSvc.Create(customer1);
        userSvc.Create(customer2);
        manager1.addGroup(managerGroup);
        manager2.addGroup(managerGroup);

        SalonCustomers sc1 = new SalonCustomers("Customer 1", "31st Chicago 60616", "dhirajj75@gmail.com", "123456789");
        sc1.setUser(customer1);
        SalonCustomers sc2 = new SalonCustomers("Customer 2", "31st Chicago 60616", "d@gmail.com", "123456789");
        sc1.setUser(customer2);
        Appointment a1 = new Appointment(LocalDate.of(2020, Month.NOVEMBER, 30), ServiceType.hairCut, LocalTime.of(10, 30));
        Appointment a2 = new Appointment(LocalDate.of(2020, Month.NOVEMBER, 30), ServiceType.hairColor, LocalTime.of(12, 30));
        Employee e1 = new Employee("Employee 1", "31st Chicago 60616", "e1@gmail.com", "123456789", EmployeeType.hairCut);
        e1.setUser(employee1);
        Employee e2 = new Employee("Employee 2", "31st Chicago 60616", "e2@gmail.com", "123456789", EmployeeType.hairColor);
        Employee e3 = new Employee("Employee 3", "31st Chicago 60616", "e3@gmail.com", "123456789", EmployeeType.skinCare);
        e2.setUser(employee2);
        e3.setUser(employee3);
        try{
        image = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\src\\main\\webapp\\resources\\images\\1.png"));
        }catch(IOException e){
            System.err.println("file"+e);
        }
        Products ps1 = new Products("Hair Cream", 4, 50, "50gms", image);
        Salon s1 = new Salon("One Cut", "Chicago", "1234567890");
        Salon s2 = new Salon("Two Cut", "Chicago", "1233467890");
        
        Manager m1 = new Manager("Manager 1", "31st Chicago", "manager1@gmail.com", "1234569870");
        Manager m2 = new Manager("Manager 2", "31st Chicago", "manager1@gmail.com", "1234569870");
        Cart c1 = new Cart(0);
        m1.setUser(manager1);
        m2.setUser(manager2);

        a1.setSalonCustomers(sc1);
        a2.setSalonCustomers(sc1);
        sc1.addAppointment(a1);
        sc1.addAppointment(a2);
        a1.setEmployee(e1);
        
      
        a2.setEmployee(e2);
        
        c1.setSalonCustomers(sc1);
       
        ps1.setSalon(s2);
        e1.setSalon(s1);
        e2.setSalon(s1);
        e3.setSalon(s2);
        s1.setManager(m1);
        m1.setSalon_flag(1);
        s1.addemployee(e1);
        s1.addemployee(e2);
        s2.setManager(m2);
        m2.setSalon_flag(1);
        s2.addemployee(e3);
        c1.addProducts(ps1);
        c1.addProducts(ps1);
        a1.setSalon(e1.getSalon());
        a2.setSalon(e2.getSalon());

        OrderHistory o = new OrderHistory(c1.getCartBalance(), "400 E 33RD Street", "Card", LocalDate.now());
        sc1.addOrderHistory(o);

        cartSvs.Create(c1);
        orderSvc.Create(o);
        salonCustomerSvc.Create(sc1);
        salonCustomerSvc.Create(sc2);
        employeeSvc.Create(e1);
        employeeSvc.Create(e2);
        employeeSvc.Create(e3);
        appointmentSvc.Create(a1);
        appointmentSvc.Create(a2);
        productSvc.Create(ps1);
        salonSvc.Create(s1);
        managerSvc.Create(m1);
        managerSvc.Create(m2);
        employeeSvc.Create(e3);
        salonSvc.Create(s2);
       

        LOG.info("\nappointments :");
        for (Appointment a : appointmentSvc.findAll()) {
            LOG.info("\n\nAppointment: " + a.toString());
        }

        LOG.info("\nEmployees :");
        for (Employee e : employeeSvc.findAll()) {
            LOG.info(e.toString());
        }
    }
}

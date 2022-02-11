package ua.com.alevel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.hibernate.*;
import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Employee;

@SpringBootTest
class Hw9HibernateApplicationApplicationTests {
/*    private static SessionFactory sessionFactory;
    private Session session;
    @Test public void testCreate() {
        System.out.println("Running testCreate...");
        session.beginTransaction();
        Employee employee = new Employee();
        employee.setFirstName("f1");
        employee.setLastName("l1");
        Integer id = (Integer) session.save(employee);
        session.getTransaction().commit();
        Assertions.assertTrue(id > 0);
    }*/

    @Test
    void contextLoads() {
        Assertions.assertFalse(1+1>1);
    }

}

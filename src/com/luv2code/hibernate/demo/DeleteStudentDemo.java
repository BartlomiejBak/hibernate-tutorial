package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory =new Configuration()
                .configure("hibernate.config.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //use the session object to save java object
        try {
            int studentId = 1;

            //start a transaction
            Session session = factory.getCurrentSession();
            System.out.println("beginning transaction");
            session.beginTransaction();

            //retrieve student based no the id
            Student myStudent = session.get(Student.class, studentId);
            System.out.println("student: " + myStudent);

            //deleting student id = 1
            //session.delete(myStudent);

            //deleting student id = 2
            session.createQuery("delete from Student where id=2").executeUpdate();

            //commit transaction
            System.out.println("committing transaction");
            session.getTransaction().commit();

        }
        finally {
            factory.close();
        }
    }
}

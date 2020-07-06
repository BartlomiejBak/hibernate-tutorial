package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory =new Configuration()
                .configure("hibernate.config.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        //use the session object to save java object
        try {
            int studentId =1;

            //start a transaction
            System.out.println("beginning transaction");
            session.beginTransaction();

            //retrieve student based no the id
            Student myStudent = session.get(Student.class, studentId);
            System.out.println("student: " + myStudent);

            System.out.println("updating name... ");
            myStudent.setFirstName("Scooby");

            //commit transaction
            System.out.println("committing transaction");
            session.getTransaction().commit();

            //NEW CODE
            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students
            System.out.println("updating email");
            session.createQuery("update Student set email='newEmail@gmail.com'").executeUpdate();

            //commit transaction
            System.out.println("committing transaction");
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}

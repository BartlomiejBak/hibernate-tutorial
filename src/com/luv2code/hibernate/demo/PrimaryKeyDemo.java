package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
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
            //create 3 student object
            System.out.println("creating a new student object");
            Student student1 = new Student("john1", "doe1", "email1");
            Student student2 = new Student("john2", "doe2", "email2");
            Student student3 = new Student("john3", "doe3", "email3");

            //start a transaction
            System.out.println("beginning transaction");
            session.beginTransaction();

            //save the student object
            System.out.println("saving student object");
            session.save(student1);
            session.save(student2);
            session.save(student3);


            //commit transaction
            System.out.println("committing transaction");
            session.getTransaction().commit();

        }
        finally {
            factory.close();
        }
    }
}

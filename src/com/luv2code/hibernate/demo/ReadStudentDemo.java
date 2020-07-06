package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
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
            //create student object
            System.out.println("creating a new student object");
            Student student = new Student("Daffy", "Duck", "email");

            //start a transaction
            System.out.println("beginning transaction");
            session.beginTransaction();

            //save the student object
            System.out.println("saving student object");
            session.save(student);

            //commit transaction
            System.out.println("committing transaction");
            session.getTransaction().commit();

            //find out the student's id: primary key
            System.out.println("saved student. Generated id: " + student.getId());

            //now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id
            System.out.println("\ngetting student with id: " + student.getId());

            Student myStudent = session.get(Student.class, student.getId());

            System.out.println("Get complete: " + myStudent);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("done");
        }
        finally {
            factory.close();
        }
    }
}

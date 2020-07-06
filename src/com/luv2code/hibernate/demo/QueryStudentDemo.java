package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
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
            //start a transaction
            System.out.println("beginning transaction");
            session.beginTransaction();

            //query students
            List<Student> studentList = session.createQuery("from Student s where s.id < 5").getResultList();
            List<Student> studentListTwo = session.createQuery("from Student s where s.firstName LIKE 'john%'").getResultList();

            //display students
            displayStudents(studentList);
            System.out.println("all Johns: ");
            displayStudents(studentListTwo);

            //commit transaction
            System.out.println("committing transaction");
            session.getTransaction().commit();

        }
        finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> studentList) {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}

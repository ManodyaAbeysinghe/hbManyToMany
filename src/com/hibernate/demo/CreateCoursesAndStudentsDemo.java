package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class CreateCoursesAndStudentsDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Instructor.class)
				                 .addAnnotatedClass(InstructorDetail.class)
				                 .addAnnotatedClass(Course.class)
				                 .addAnnotatedClass(Review.class)
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
	
		//create session
		
		Session session = factory.getCurrentSession();
		
		try {

			//start a transaction
			session.beginTransaction();
			
		   //create a course
		  Course tempCourse = new Course("Pacman - How To Score One MILLION Points");
			
			
		//save the course
		  System.out.println("\nSaving the course...");
		  session.save(tempCourse);
		  System.out.println("Send the course: "+tempCourse);
			
		  //create the students
		  Student tempStudent1 = new Student("John","Doe","john@code.com");
		  Student tempStudent2 = new Student("Mary","Public","Mary@code.com");
		  
		  //add students to the course
		  tempCourse.addStudent(tempStudent1);
		  tempCourse.addStudent(tempStudent2);
		  
		  //save the students
		  System.out.println("\nSaving students...");
		  session.save(tempStudent1);
		  session.save(tempStudent2);
		  System.out.println("Saved students: "+ tempCourse.getStudents());
		  
		  
		  session.save(tempCourse);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}
        finally {
        	//add clean up code
        	session.close();
        	factory.close();
        	
        }
	}

}

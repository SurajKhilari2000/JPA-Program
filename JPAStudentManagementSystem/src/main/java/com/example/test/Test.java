package com.example.test;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.example.entity.Student;

public class Test {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
	private static Scanner sc = new Scanner(System.in);

	public static void insertStudent() {

	 	System.out.println("Enter student name:");
		String name = sc.next();
		System.out.println("Enter student date of birth (YYYY-MM-DD):");
		String dob = sc.next();
		System.out.println("Enter student percentage:");
		double percentage = sc.nextDouble();
		System.out.println("Enter student city:");
		String city = sc.next();
		System.out.println("Enter student email id:");
		String email = sc.next();
		System.out.println("Enter student phone number:");
		String phone = sc.next();

		Student student = new Student();
		student.setStudentName(name);
		student.setDob(dob);
		student.setPercentage(percentage);
		student.setCity(city);
		student.setEmail_id(email);
		student.setPhone_no(phone);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(student);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println(student);

	}

	public static void allStudents() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		List<Student> students = entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();

		for (Student student : students) {
			System.out.println(student);
		}
	}

	public static void findStudentByCity() {
		System.out.println("Enter City : ");
		String getCity = sc.nextLine();
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT s FROM Student s where s.city = :city");
		query.setParameter("city", getCity);
		List<Student> students = query.getResultList();
		System.out.println("Fetching Records on the Basis of City : ");
		for (Student student : students) {
			System.out.println(student);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static void updateStudentDetailsByRollNo() {
		
		System.out.println("Enter Student Roll Number : ");
		int rollNo = sc.nextInt();
		System.out.println("Enter student name:");
		String newName = sc.next();
		System.out.println("Enter student date of birth (YYYY-MM-DD):");
		String newDob = sc.next();
		System.out.println("Enter student percentage:");
		double newPercentage = sc.nextDouble();
		System.out.println("Enter student city:");
		String newCity = sc.next();
		System.out.println("Enter student email id:");
		String newEmail = sc.next();
		System.out.println("Enter student phone number:");
		String newPhoneNo = sc.next();
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Student student = entityManager.find(Student.class, rollNo);
		if (student != null) {
			student.setStudentName(newName);
			student.setDob(newDob);
			student.setPercentage(newPercentage);
			student.setCity(newCity);
			student.setEmail_id(newEmail);
			student.setPhone_no(newPhoneNo);
			entityManager.getTransaction().commit();
		} else {
			System.out.println("Not Found.");
		}
	}

	public static void deleteStudentByRollNo() {
		System.out.println("Enter Student Roll Number : ");
		int rollNo = sc.nextInt();
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Student student = entityManager.find(Student.class, rollNo);
		if (student != null) {
			entityManager.remove(student);
			entityManager.getTransaction().commit();
			System.out.println("Deleted Record Successfully!");
		} else {
			System.out.println("Not Found.");
		}
	}

	public static void getStudentByPercentageRange() {
		System.out.println("Enter Minimum Percentage : ");
		double minPercent = sc.nextDouble();
		System.out.println("Enter Maximum Percentage : ");
		double maxPercent = sc.nextDouble();
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager
				.createQuery("SELECT s FROM Student s where s.percentage BETWEEN :minPercent AND :maxPercent");
		query.setParameter("minPercent", minPercent);
		query.setParameter("maxPercent", maxPercent);
		List<Student> students = query.getResultList();
		System.out.println("Record Fetch Between Min Percentage and MaX Percentage: ");
		for (Student student : students) {
			System.out.println(student);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static void displayStudentDetailsOfFirstRank() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT s from Student s order by s.percentage desc");
		query.setMaxResults(1);
		List<Student> students = query.getResultList();
		System.out.println("Display 1st Rank");
		for (Student student : students) {
			System.out.println(student);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static void searchByStudentRollNo() {
		System.out.println("Enter Roll No to Get Student Detail: ");
		int rollNo = sc.nextInt();
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT s FROM Student s where s.rollNo = :rollNo");
		query.setParameter("rollNo", rollNo);
		List<Student> students = query.getResultList();
		System.out.println("Searched Student by Roll No : ");
		for (Student student : students) {
			System.out.println(student);
		}
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	public static void sortStudentByPercent() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT s from Student s order by s.percentage");
		List<Student> students = query.getResultList();
		System.out.println("Sort Student By Percentage: ");
		for (Student student : students) {
			System.out.println(student);
		}
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	public static void displayStudentsOfSameCity() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT s.city, COUNT(s) FROM Student s GROUP BY s.city");
		List<Object[]> results = query.getResultList();

		for (Object[] result : results) {
			String city = (String) result[0];
			Long count = (Long) result[1];
			if (count > 1) {
				System.out.println("Student living in" + city + ":");
				Query studentQuery = entityManager.createQuery("SELECT s FROM Student s WHERE s.city = :city");
				studentQuery.setParameter("city", city);

				List<Student> students = studentQuery.getResultList();
				System.out.println("Display City By Same City:  ");
				for (Student student : students) {
					System.out.println(student);
				}
				//entityManager.getTransaction().commit();
				// entityManager.close();

			}
		}

	}

	public static void main(String[] args) {
		
		
		int choice; 
		do { 
	    System.out.println("Choose an Option: ");
		System.out.println("1. Insert Record");
		System.out.println("2. Fetched Student Details");
		System.out.println("3. Find Student By City");
		System.out.println("4. Update Student Details ");
		System.out.println("5. Delete Student Details ");
		System.out.println("6. Student Retrive By Range");
		System.out.println("7. Details of First Rank");
		System.out.println("8. Search Student By RollNo");
		System.out.println("9. Sort Students By Percentage");
		System.out.println("10. Display Same City");
		System.out.println("11. Exit");
		System.out.println("Enter Your Choice: ");
		choice = sc.nextInt();
		sc.nextLine();
		
		switch (choice) {
		case 1:
			insertStudent();
			break;
		case 2:
			allStudents();
			break;
		case 3:
			findStudentByCity();
			break;
		case 4:
			updateStudentDetailsByRollNo();
			break;
		case 5:
			deleteStudentByRollNo();
			break;
		case 6:
			getStudentByPercentageRange();
			break;
		case 7:
			displayStudentDetailsOfFirstRank();
			break;
		case 8:
			searchByStudentRollNo();
			break;
		case 9:	
			sortStudentByPercent();
			break;
		case 10:
			displayStudentsOfSameCity();
			break;
		case 11:
			System.out.println("Exiting......");
			break;
		default: 
			System.out.println("Invalid Choice");
		}
		}while(choice != 11);
		 

	}

}

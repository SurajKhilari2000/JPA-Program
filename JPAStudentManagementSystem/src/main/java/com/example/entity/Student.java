package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Student_Table")
public class Student {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollNo;
	
	private String studentName;
	
	private String dob;
	
	private double percentage;
	
	private String city; 
	
	private String email_id;
	
	private String phone_no;
	
	public Student() {
		
	}

	public Student(int rollNo, String studentName, String dob, double percentage, String city, String email_id,
			String phone_no) {
		super();
		this.rollNo = rollNo;
		this.studentName = studentName;
		this.dob = dob;
		this.percentage = percentage;
		this.city = city;
		this.email_id = email_id;
		this.phone_no = phone_no;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", studentName=" + studentName + ", dob=" + dob + ", percentage="
				+ percentage + ", city=" + city + ", email_id=" + email_id + ", phone_no=" + phone_no + "]";
	}
	
	

}

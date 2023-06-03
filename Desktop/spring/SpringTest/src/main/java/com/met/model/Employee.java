package com.met.model;

public class Employee {
	
	private int id;
	private String name;
	private String designation;
	private String emailid;
	
	
	
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Employee(int id, String name, String designation, String emailid) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.emailid = emailid;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", emailid=" + emailid + "]";
	}
	
	

}

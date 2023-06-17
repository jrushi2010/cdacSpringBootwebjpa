package com.met.cdac.springbootweb.service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.met.cdac.springbootweb.dao.EmployeeDao;
import com.met.cdac.springbootweb.model.Employee;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeDao empdao;
	
	public void saveEmployee(Employee employee) {
		
		//System.out.println(empdao.getClass());
		
		//perform save validation on employee object.
		
		String emailid = employee.getEmailid();
		
		if(!emailid.contains(".")||!emailid.contains("@")) {
			throw new RuntimeException("EmailId : " + emailid + " is invalid");
		}
		
		System.out.println("EmployeeService :: saveEmployee");
		
		empdao.saveEmployee(employee);
	
	}
	
	public Collection<Employee> getAllEmployees(){
		
		return empdao.getAllEmployees();
	}
	
	public Employee getEmployee(int id) {
		return empdao.getEmployee(id);
	}

}

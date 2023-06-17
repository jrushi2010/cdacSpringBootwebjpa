package com.met.cdac.springbootweb.service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.met.cdac.springbootweb.dao.EmployeeDao;
import com.met.cdac.springbootweb.dao.EmployeeDaoRepository;
import com.met.cdac.springbootweb.model.Employee;


@Service
public class EmployeeService {
	
//	@Autowired
//	EmployeeDao empdao;
	
	@Autowired
	private EmployeeDaoRepository employeeDaoRepository;
	
	
	
	@Transactional
	public void saveEmployee(Employee employee) {
		
		//System.out.println(empdao.getClass());
		
		//perform save validation on employee object.
		
		String emailid = employee.getEmailid();
		
		if(!emailid.contains(".")||!emailid.contains("@")) {
			throw new RuntimeException("EmailId : " + emailid + " is invalid");
		}
		
		System.out.println("EmployeeService :: saveEmployee");
		
		//empdao.saveEmployee(employee);
		
		employeeDaoRepository.save(employee);
	
	}
	
	@Transactional(readOnly = true)
	public Collection<Employee> getAllEmployees(){
		
		//return empdao.getAllEmployees();
		//return empdao.getAllEmployeesUsingJpa();
		return employeeDaoRepository.findAll();
	}
	
	@Transactional
	public Employee getEmployee(int id) {
		//return empdao.getEmployee(id);
		//return empdao.getEmployeeUsingJpa(id);
		return employeeDaoRepository.findById(id).get();
	}
	
	

}

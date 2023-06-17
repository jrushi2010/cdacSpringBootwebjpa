package com.met.cdac.springbootweb.controller;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.met.cdac.springbootweb.model.Employee;
import com.met.cdac.springbootweb.service.EmployeeService;


//@Controller
@RestController  //combination of @controller and @responsebdy
@RequestMapping("/welcome")
public class EmployeeRestController {

//	@GetMapping
//	public @ResponseBody String greetings() {
//		return "Welcome to spring rest";
//	}
	
	@GetMapping
	public String greetings() {
		return " welcome to spring rest ... ";
	}
	
	@Autowired
	EmployeeService empservice;
	
	@GetMapping(value="getEmp/{id}",produces= {
			MediaType.APPLICATION_JSON_VALUE
			})
	public Employee getEmployee(@PathVariable int id) {
		return empservice.getEmployee(id);
	}
	
//	@GetMapping(value="getXMLEmp/{id}",produces=MediaType.APPLICATION_XML_VALUE)
//	public Employee getXMLEmployee(@PathVariable int id) {
//		return empservice.getEmployee(id);
//	}
	
	@PostMapping(value="saveEmp",consumes= MediaType.APPLICATION_JSON_VALUE)
	public String saveEmployee(@RequestBody Employee employee) {
		empservice.saveEmployee(employee);
		return "success";
		
	}
	
}

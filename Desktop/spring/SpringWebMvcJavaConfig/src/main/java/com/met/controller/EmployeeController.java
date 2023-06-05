package com.met.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.met.model.Employee;
import com.met.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService empserv ;
	
	@GetMapping
	public ModelAndView initializeEmployee()
	{
		ModelAndView mv = new ModelAndView();
		
		Employee empl = new Employee();
		empl.setDesignation("Fresher");
		mv.addObject("employee",empl);
		
		Collection<Employee> allEmployees = empserv.getAllEmployees();
		mv.addObject("allEmployees",allEmployees);
		
		mv.setViewName("emp");
		return mv;
	}
	
	@PostMapping
	public ModelAndView submitEmployee(@ModelAttribute Employee employee)
	{
		ModelAndView mv = new ModelAndView();
		
		System.out.println("From JSP to Employee controller we get this data of "+employee);
		
		empserv.saveEmployee(employee);
		
		Employee empl = new Employee();
		empl.setDesignation("Fresher");
		mv.addObject("employee",empl);
		
		Collection<Employee> allEmployees = empserv.getAllEmployees();
		mv.addObject("allEmployees",allEmployees);
		
		
		
		mv.setViewName("emp");
		
		return mv;
	}
	
	@ExceptionHandler
	public ModelAndView handleException(Exception e, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		
		mv.addObject("errMsg",e.getMessage());
		
		mv.addObject("url",request.getRequestURI());
		
		mv.setViewName("error");
		
		
		return mv;
	}
	
	
	

}

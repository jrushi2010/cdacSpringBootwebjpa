package com.met.cdac.springbootweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.met.cdac.springbootweb.model.Employee;


@Repository
public class EmployeeDao {
	
	private Map<Integer, Employee> mapEmployee = new HashMap<>();
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void saveUsingDataSource(Employee employee) {
		
		try(Connection con = datasource.getConnection();
				PreparedStatement pstmt = con.prepareStatement("insert into EmployeeTbl values(?,?,?,?)")){
			
			pstmt.setInt(1, employee.getId());
			pstmt.setString(2, employee.getName());
			pstmt.setString(3, employee.getDesignation());
			pstmt.setString(4, employee.getEmailid());
			
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveUsingJDBCTemplate(Employee employee) {
		
		System.out.println("saving employee using jdbc template");
		
		jdbcTemplate.update("insert into EmployeeTbl values(?,?,?,?)",
				new Object[] {employee.getId(),employee.getName(),
						employee.getDesignation(),employee.getEmailid()});
	}
	
	@Transactional
	public void saveEmployee(Employee employee) {
		
		System.out.println("EmployeeDao :: saveEmployee" + employee);
		
		saveUsingJDBCTemplate(employee);
		}
	
	


	
	public Collection<Employee> getAllEmployees(){
		return jdbcTemplate.query("select * from employeeTbl", 
				new BeanPropertyRowMapper<Employee>(Employee.class));
		
		//return mapEmployee.values();
	}
	
	public Employee getEmployee(int id) {
		return jdbcTemplate.queryForObject("select * from employeetbl where id="+id,
				new BeanPropertyRowMapper<Employee>(Employee.class));
		
	}
	

}

package com.met.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.met.model.Employee;

@Repository
public class EmployeeDao {
	
	private Map<Integer, Employee> mapEmployee = new HashMap<>();
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
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
	
	public void saveUsingHibernate(Employee employee) {
		
		System.out.println("save employee using hibernate");
		
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			session.save(employee);
			
			tx.commit();
			
		}catch(HibernateException ex) {
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
		}finally {
			if(session != null)
				session.clear();
		}
	}
	
	
	//@Transactional(rollbackFor=Exception.class, noRollbackFor=NullPointerException.class)
	@Transactional(rollbackFor=Exception.class)
	public void saveEmployee(Employee employee) {
		
		System.out.println("EmployeeDao :: saveEmployee" + employee);
		//mapEmployee.put(employee.getId(), employee);
		
		//saveUsingDataSource(employee);
		
		//saveUsingJDBCTemplate(employee);
		
		//saveUsingHibernate(employee);
		
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
		
		//save it into db
		
		updateEmployeeCount();
	}
	
	public void updateEmployeeCount() {
		
		Session session = sessionFactory.getCurrentSession();
		session.createSQLQuery("update employeecount set count=count+1").executeUpdate();
	}
	
//	class BeanPropertyRowMapper implements RowMapper<Employee>{
//		
//		public Employee mapRow(ResultSet rs,int arg1) throws SQLException {
//			
//			Employee emp = new Employee();
//			emp.setId(rs.getInt(1));
//			emp.setName(rs.getString(2));
//			emp.setDesignation(rs.getString(3));
//			emp.setEmailid(rs.getString(4));
//			
//			return emp;
//		}
//	}

	
	public Collection<Employee> getAllEmployees(){
		
		
		return jdbcTemplate.query("select * from employeeTbl", 
				new BeanPropertyRowMapper<Employee>(Employee.class));
		
		//return mapEmployee.values();
	}
	
	@Transactional(readOnly=true)
	public Employee getEmployee(int id) {
		jdbcTemplate.queryForObject("select * from employeetbl where id="+id,
				new BeanPropertyRowMapper<Employee>(Employee.class));
		
		Session session = sessionFactory.getCurrentSession();
		Employee object = (Employee)session.get(Employee.class, id);
		return object;
	}

}

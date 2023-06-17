package com.met.cdac.springbootweb;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@SpringBootApplication //combination of @ComponentScan + @Configuration + @EnableAutoConfiguration
public class SpringbootwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootwebApplication.class, args);
	}
	
	//using exlcude we are telling to spring boot dont use your data source, use my datasource
	//thats why we write our own datasource
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dm = new DriverManagerDataSource();
//		dm.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dm.setUrl("jdbc:mysql://localhost:3306/employee");
//		dm.setUsername("root");
//		dm.setPassword("3528Rushikesh@rj95");
//		
//		return dm;
//	}

}

package com.met.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.met.model.Employee;

@Configuration
@ComponentScan(basePackages="com.met.*")	//<context:component-scan base-package="com.met.*" />
@EnableWebMvc							//<mvc:annotation-driven />
@EnableTransactionManagement			//<tx:annotation-driven transaction-manager="hibernateTransactionManager"/>
public class MVCConfig {

	
	/*<bean
	class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<property name="prefix">
		<value>/WEB-INF/pages/</value>
	</property>
	<property name="suffix">
		<value>.jsp</value>
	</property>
</bean>*/
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/pages/");
		internalResourceViewResolver.setSuffix(".jsp");
		
		return internalResourceViewResolver;
	}
	
	/*<bean id="myOracledataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
	<property name="driverClassName" value="oracle.jdbc.OracleDriver" />
	<property name="url" value="jdbc:oracle:thin:@localhost:1521:XE" />
	<property name="username" value="system" />
	<property name="password" value="Dinesh@123" />
</bean>*/
	
	@Bean("myOracledataSource")
	public DataSource dataSource(){
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dm.setUrl("jdbc:mysql://localhost:3306/employee");
		dm.setUsername("root");
		dm.setPassword("3528Rushikesh@rj95");
		
		return dm;
	}
	
	/*<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate" >
		<property name="dataSource" ref="myOracledataSource"></property>
</bean>*/
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		//jdbcTemplate.setDataSource(dataSource());
		
		jdbcTemplate.setDataSource(ds);
		
		return jdbcTemplate;
	}
	
	/*<bean id="sessionFactoryBean" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
	<property name="dataSource" ref="myOracledataSource"></property>
	<property name="annotatedClasses">
		<list>
			<value>com.met.model.Employee</value>
		</list>
	</property>
	<property name="hibernateProperties">
		<props>
			<prop key="hibernate.dialect">org.hibernate.dialect.Oracle10gDialect</prop>
			<prop key="hibernate.show_sql">true</prop>
		</props>
	</property>
 </bean>*/
	
	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean(){
		
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		
		localSessionFactoryBean.setDataSource(dataSource());
		localSessionFactoryBean.setAnnotatedClasses(Employee.class);
		
		localSessionFactoryBean.setHibernateProperties(hibernateProperties());
		
		return localSessionFactoryBean;
		
	}

	@Bean
	public Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.show_sql", "true");
		
		return properties;
	}
	
	/*<bean id="hibernateTransactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">
	<property name="sessionFactory" ref="sessionFactoryBean"></property>
 </bean>*/
	
	@Bean
	public HibernateTransactionManager htm(SessionFactory sf){
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sf);
		
		return htm;
	}
	
}











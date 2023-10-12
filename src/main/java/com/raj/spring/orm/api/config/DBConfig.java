package com.raj.spring.orm.api.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@PropertySource(value = { "classpath:application.properties" })
@Configuration
public class DBConfig {

	@Value("${spring.datasource.driver-class-name}")
	private String drvierClass;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String passWord;
	
	@Value("${spring.jpa.database-platform}")
	private String dialect;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, userName, passWord);
		dataSource.setDriverClassName(drvierClass);

		return dataSource;
	}
	
	/**
	 * JPA by default search sessionFactory by name 'entityManagerFactory'
	 * @return
	 */
	@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(getDataSource());
		factory.setHibernateProperties(hibernateProperties());
		factory.setPackagesToScan(new String[]{ "com.raj.spring.orm.api.model" });
		
		return factory;
	}
	
	private Properties hibernateProperties() {
		Properties pros = new Properties();
		pros.put("hibernate.dialect", dialect);
		pros.put("hibernate.hbm2ddl.auto", "update");
		pros.put("hibernate.show_sql", "true");
		pros.put("hibernate.format_sql", "true");

		return pros;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transcationManager(SessionFactory factory) {
		HibernateTransactionManager transManager = new HibernateTransactionManager();
		transManager.setSessionFactory(factory);
		
		return transManager;
	}

	
}

package com.raj.spring.orm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

// the problem was with @EnableAutoConfiguration - use exclude=HibernateJpaAutoConfiguration.class
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class SpringBootHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHibernateApplication.class, args);
	}

}

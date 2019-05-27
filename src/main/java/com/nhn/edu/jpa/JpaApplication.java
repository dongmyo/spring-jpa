package com.nhn.edu.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = SpringApplication.run(JpaApplication.class, args)) {
			// nothing
		}
	}

}

package com.nhn.edu.jpa;

import com.nhn.edu.jpa.service.MemberService;
import com.nhn.edu.jpa.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = SpringApplication.run(JpaApplication.class, args)) {
			// nothing
		}
	}

	@Bean
	CommandLineRunner onStartUp(MemberService memberService, OrderService orderService) {
		return args -> {
			memberService.doSomething();
			orderService.doSomething();
		};
	}

}

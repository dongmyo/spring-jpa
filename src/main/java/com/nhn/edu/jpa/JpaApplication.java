package com.nhn.edu.jpa;

import com.nhn.edu.jpa.service.ItemService;
import com.nhn.edu.jpa.service.MemberService;
import com.nhn.edu.jpa.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = SpringApplication.run(JpaApplication.class, args)) {
			// nothing
		}
	}

	@Bean
	CommandLineRunner onStartUp(OrderService orderService,
								ItemService itemService,
								MemberService memberService) {
		return args -> {
			itemService.setUp();

			long price = 200L;

			log.debug("items greater than {} : {}",
					  price,
					  itemService.getItemNamesByPriceMoreThan(100).size());

			orderService.setUp();
			log.debug("orders={}", orderService.getOrdersAsJson());

			memberService.setUp();
			log.debug("members={}", memberService.getMembersAsJson());
		};
	}

}

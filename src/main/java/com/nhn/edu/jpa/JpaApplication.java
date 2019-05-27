package com.nhn.edu.jpa;

import com.nhn.edu.jpa.entity.ProjectMember;
import com.nhn.edu.jpa.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = SpringApplication.run(JpaApplication.class, args)) {
			// nothing
		}
	}

	@Bean
	CommandLineRunner onStartUp(ProjectService projectService) {
		return args -> {
			Long projectId = projectService.saveSomethingAndReturnProjectId();

			List<ProjectMember> projectMembers = projectService.getProjectMembers(projectId);
			log.debug("{}", projectMembers.size());
		};
	}

}

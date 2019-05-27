package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

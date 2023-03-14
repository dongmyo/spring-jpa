package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

package com.nhn.edu.jpa.service;

import com.nhn.edu.jpa.entity.Course;
import com.nhn.edu.jpa.entity.Enrollment;
import com.nhn.edu.jpa.entity.Student;
import com.nhn.edu.jpa.repository.CourseRepository;
import com.nhn.edu.jpa.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @Transactional
    public void setUp() {
        Course course1 = new Course();
        course1.setCourseName("jpa");

        Course course2 = new Course();
        course2.setCourseName("boot");

        courseRepository.saveAll(Arrays.asList(course1, course2));

        Student student1 = new Student();
        student1.setName("nhn");

        Enrollment enrollment1 = new Enrollment();
        enrollment1.setCourse(course1);
        enrollment1.setEnrolledAt(LocalDateTime.now());
        enrollment1.setStudent(student1);

        Enrollment enrollment2 = new Enrollment();
        enrollment2.setCourse(course2);
        enrollment2.setEnrolledAt(LocalDateTime.now());
        enrollment2.setStudent(student1);

        student1.getEnrollments().add(enrollment1);
        student1.getEnrollments().add(enrollment2);

        Student student2 = new Student();
        student2.setName("dooray");

        Enrollment enrollment3 = new Enrollment();
        enrollment3.setCourse(course1);
        enrollment3.setEnrolledAt(LocalDateTime.now());
        enrollment3.setStudent(student2);

        student2.getEnrollments().add(enrollment3);

        studentRepository.saveAll(Arrays.asList(student1, student2));
    }

    @Transactional(readOnly = true)
    public List<Course> getAllEnrolledCourses() {
        return studentRepository.getStudentsWithAssociations()
            .stream()
            .map(Student::getEnrollments)
            .flatMap(Collection::stream)
            .map(Enrollment::getCourse)
            .collect(Collectors.toList());
    }

}

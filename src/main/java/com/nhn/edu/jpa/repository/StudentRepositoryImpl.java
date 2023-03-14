package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.QCourse;
import com.nhn.edu.jpa.entity.QEnrollment;
import com.nhn.edu.jpa.entity.QStudent;
import com.nhn.edu.jpa.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class StudentRepositoryImpl extends QuerydslRepositorySupport implements StudentRepositoryCustom {
    public StudentRepositoryImpl() {
        super(Student.class);
    }

    @Override
    public List<Student> getStudentsWithAssociations() {
        QStudent student = QStudent.student;
        QEnrollment enrollment = QEnrollment.enrollment;
        QCourse course = QCourse.course;

        return from(student)
            .leftJoin(student.enrollments, enrollment).fetchJoin()
            .innerJoin(enrollment.course, course).fetchJoin()
            .fetch();
    }

}

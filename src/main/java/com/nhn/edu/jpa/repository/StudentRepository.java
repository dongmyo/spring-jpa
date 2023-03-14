package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // TODO: JPQL join fetch를 이용해서 N + 1 문제 해결하기
    @Query("select s from Student s")
    List<Student> getStudentsWithAssociations();

}

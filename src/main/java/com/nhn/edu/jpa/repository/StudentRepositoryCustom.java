package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Student;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StudentRepositoryCustom {
    List<Student> getStudentsWithAssociations();

}

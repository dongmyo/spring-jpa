package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.ProjectMember;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectMemberRepositoryCustom {
    List<ProjectMember> getProjectMembers(Long projectId);

}

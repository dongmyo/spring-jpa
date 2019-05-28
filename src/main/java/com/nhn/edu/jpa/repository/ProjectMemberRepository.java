package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends ProjectMemberRepositoryCustom,
        JpaRepository<ProjectMember, Long> {
    List<ProjectMember> findByProject_ProjectId(Long projectId);

}

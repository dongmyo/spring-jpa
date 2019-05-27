package com.nhn.edu.jpa.service;

import com.nhn.edu.jpa.entity.Project;
import com.nhn.edu.jpa.entity.ProjectMember;
import com.nhn.edu.jpa.repository.ProjectMemberRepository;
import com.nhn.edu.jpa.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final EntityManager entityManager;


    public ProjectService(ProjectRepository projectRepository,
                          ProjectMemberRepository projectMemberRepository,
                          EntityManager entityManager) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.entityManager = entityManager;
    }


    @Transactional
    public Long saveSomethingAndReturnProjectId() {
        Project project = new Project();
        project.setName("project1");

        Project savedProject = projectRepository.save(project);

        List<ProjectMember> members = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            ProjectMember projectMember = new ProjectMember();
            projectMember.setProject(savedProject);
            projectMember.setMemberId((long) (i + 1));

            members.add(projectMember);
        }

        projectMemberRepository.saveAll(members);
        entityManager.clear();

        return savedProject.getProjectId();
    }

    public List<ProjectMember> getProjectMembers(Long projectId) {
        return projectMemberRepository.findByProject_ProjectId(projectId);
    }

}

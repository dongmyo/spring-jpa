package com.nhn.edu.jpa.service;

import com.nhn.edu.jpa.entity.Project;
import com.nhn.edu.jpa.entity.ProjectMember;
import com.nhn.edu.jpa.repository.ProjectMemberRepository;
import com.nhn.edu.jpa.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final EntityManager entityManager;
    private final Resource resourceFile;


    public ProjectService(ProjectRepository projectRepository,
                          ProjectMemberRepository projectMemberRepository,
                          EntityManager entityManager,
                          @Value("classpath:data/100000.txt") Resource resourceFile) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.entityManager = entityManager;
        this.resourceFile = resourceFile;
    }


    @Transactional
    public Long saveSomethingAndReturnProjectId() {
        Project project = new Project();
        project.setName("project1");
        project.setDescription(readResourceFileContent());

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
        return projectMemberRepository.getProjectMembers(projectId);
    }

    private String readResourceFileContent() {
        String content = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.resourceFile.getInputStream()))) {
            content = reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            log.error("", e);
        }

        return content;
    }

}

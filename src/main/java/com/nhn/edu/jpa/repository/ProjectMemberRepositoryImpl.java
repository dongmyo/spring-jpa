package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.ProjectMember;
import com.nhn.edu.jpa.entity.QProject;
import com.nhn.edu.jpa.entity.QProjectMember;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectMemberRepositoryImpl extends QuerydslRepositorySupport
        implements ProjectMemberRepositoryCustom {
    public ProjectMemberRepositoryImpl() {
        super(ProjectMember.class);
    }


    @Override
    public List<ProjectMember> getProjectMembers(Long projectId) {
        QProjectMember projectMember = QProjectMember.projectMember;
        QProject project = QProject.project;

        return from(projectMember)
                .innerJoin(projectMember.project, project)
                    .fetchJoin() /* !!! */
                .where(projectMember.project.projectId.eq(projectId))
                .fetch();
    }

}

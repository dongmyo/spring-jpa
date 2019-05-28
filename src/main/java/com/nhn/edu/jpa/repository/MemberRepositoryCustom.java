package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface MemberRepositoryCustom {
    List<Member> getMembersWithAssociation();

    Page<Member> getPagedMembersWithAssociations(Pageable pageable);

}

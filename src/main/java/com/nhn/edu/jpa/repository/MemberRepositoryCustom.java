package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Member;

import java.util.List;

// TODO : Custom Repository interface 선언하고 구현 클래스 작성
public interface MemberRepositoryCustom {
    List<Member> getMembersWithAssociation();

}

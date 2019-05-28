package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // TODO : use join fetch
    @Query("select m from Member m")
    List<Member> getMembersWithAssociation();

}

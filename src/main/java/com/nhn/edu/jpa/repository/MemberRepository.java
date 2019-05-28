package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends MemberRepositoryCustom, JpaRepository<Member, Long> {

}

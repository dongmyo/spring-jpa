package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.dto.MemberDto;
import com.nhn.edu.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Collection<MemberDto> findAllBy();

}

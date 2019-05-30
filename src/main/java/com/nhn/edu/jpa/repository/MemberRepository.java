package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.dto.MemberDescriptionOnly;
import com.nhn.edu.jpa.dto.MemberDto;
import com.nhn.edu.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Collection<MemberDto> findAllBy();
    Collection<MemberDescriptionOnly> findByDetails_Pk_Type(String type);

}

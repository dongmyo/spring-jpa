package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.annotation.Question;
import com.nhn.edu.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // TODO : 다음 메서드의 이름을 아래 쿼리가 수행되도록 Repository 메서드 이름 규칙에 맞춰 수정하세요.
    //
    //        select m from Member m inner join MemberDetail md where md.type = ?
    //
    @Question
    List<Member> changeThistMethodName(String type);

}

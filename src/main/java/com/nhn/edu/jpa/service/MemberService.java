package com.nhn.edu.jpa.service;

import com.nhn.edu.jpa.entity.Member;
import com.nhn.edu.jpa.entity.MemberDetail;
import com.nhn.edu.jpa.repository.MemberDetailRepository;
import com.nhn.edu.jpa.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberDetailRepository memberDetailRepository;


    public MemberService(MemberRepository memberRepository,
                         MemberDetailRepository memberDetailRepository) {
        this.memberRepository = memberRepository;
        this.memberDetailRepository = memberDetailRepository;
    }


    @Transactional
    public void doSomething() {
        Member member = new Member();
        member.setName("member1");
        member.setCreateDate(LocalDateTime.now());

        Member savedMember = memberRepository.save(member);

        MemberDetail memberDetail1 = new MemberDetail();
        memberDetail1.setMember(savedMember);
        memberDetail1.setType("type1");
        memberDetail1.setDescription("member1-type1");

        memberDetailRepository.save(memberDetail1);

        MemberDetail memberDetail2 = new MemberDetail();
        memberDetail2.setMember(savedMember);
        memberDetail2.setType("type2");
        memberDetail2.setDescription("member1-type2");

        memberDetailRepository.save(memberDetail2);
    }

}

package com.nhn.edu.jpa.service;

import com.nhn.edu.jpa.entity.Member;
import com.nhn.edu.jpa.entity.MemberDetail;
import com.nhn.edu.jpa.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Transactional
    public void doSomething() {
        Member member = new Member();
        member.setName("member1");
        member.setCreateDate(LocalDateTime.now());

        Member savedMember = memberRepository.save(member);

        MemberDetail.Pk pk1 = new MemberDetail.Pk();
        pk1.setMemberId(savedMember.getMemberId());
        pk1.setType("type1");

        MemberDetail memberDetail1 = new MemberDetail();
        memberDetail1.setPk(pk1);
        memberDetail1.setDescription("member1-type1");

        MemberDetail.Pk pk2 = new MemberDetail.Pk();
        pk2.setMemberId(savedMember.getMemberId());
        pk2.setType("type2");

        MemberDetail memberDetail2 = new MemberDetail();
        memberDetail2.setPk(pk2);
        memberDetail2.setDescription("member1-type2");

        member.getDetails().add(memberDetail1);
        member.getDetails().add(memberDetail2);
    }

}

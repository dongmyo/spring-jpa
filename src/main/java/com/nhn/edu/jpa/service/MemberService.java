package com.nhn.edu.jpa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void setUp() {
        Member member1 = new Member();
        member1.setName("member1");
        member1.setCreateDate(LocalDateTime.now());

        MemberDetail.Pk pk1 = new MemberDetail.Pk();
        pk1.setType("type1");

        MemberDetail memberDetail1 = new MemberDetail();
        memberDetail1.setPk(pk1);
        memberDetail1.setDescription("member1-type1");
        memberDetail1.setMember(member1);

        MemberDetail.Pk pk2 = new MemberDetail.Pk();
        pk2.setType("type2");

        MemberDetail memberDetail2 = new MemberDetail();
        memberDetail2.setPk(pk2);
        memberDetail2.setDescription("member1-type2");
        memberDetail2.setMember(member1);

        member1.getDetails().add(memberDetail1);
        member1.getDetails().add(memberDetail2);

        memberRepository.save(member1);
    }

    @Transactional(readOnly = true)
    public String getMembersAsJson() throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter()
                                 .writeValueAsString(memberRepository.findAllBy());
    }

    @Transactional(readOnly = true)
    public String getMembersByTypeAsJson(String type) throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter()
                                 .writeValueAsString(memberRepository.findByDetails_Pk_Type(type));
    }

}

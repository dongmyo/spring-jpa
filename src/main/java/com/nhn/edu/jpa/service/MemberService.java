package com.nhn.edu.jpa.service;

import com.nhn.edu.jpa.entity.Member;
import com.nhn.edu.jpa.entity.MemberDetail;
import com.nhn.edu.jpa.repository.MemberDetailRepository;
import com.nhn.edu.jpa.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
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
        ReflectionTestUtils.invokeSetterMethod(member, "name", "member1");
        ReflectionTestUtils.invokeSetterMethod(member, "createDate", LocalDateTime.now());

        Member savedMember = memberRepository.save(member);
        Assert.notNull(savedMember, "savedMember cannot be null");

        Optional<Member> dbMember = memberRepository.findById((Long) ReflectionTestUtils.invokeGetterMethod(savedMember, "memberId"));
        if (!dbMember.isPresent()) {
            throw new IllegalStateException("member entity not found");
        }

        MemberDetail memberDetail = new MemberDetail();
        ReflectionTestUtils.invokeSetterMethod(memberDetail, "member", member);
        ReflectionTestUtils.invokeSetterMethod(memberDetail, "type", "type1");
        ReflectionTestUtils.invokeSetterMethod(memberDetail, "description", "member1-type1");

        MemberDetail savedMemberDetail = memberDetailRepository.save(memberDetail);
        Assert.notNull(savedMemberDetail, "savedMemberDetail cannot be null");

        Optional<MemberDetail> dbMemberDetail = memberDetailRepository.findById((Long) ReflectionTestUtils.invokeGetterMethod(savedMemberDetail, "memberDetailId"));
        if (!dbMemberDetail.isPresent()) {
            throw new IllegalStateException("memberDetail entity not found");
        }
    }

}

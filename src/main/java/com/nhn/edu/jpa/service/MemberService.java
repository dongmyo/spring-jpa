package com.nhn.edu.jpa.service;

import com.nhn.edu.jpa.annotation.Question;
import com.nhn.edu.jpa.entity.Member;
import com.nhn.edu.jpa.entity.MemberDetail;
import com.nhn.edu.jpa.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> getType1MembersDescriptions() {
        Class<?> clazz = Arrays.stream(memberRepository.getClass().getInterfaces())
                               .filter(iface -> iface.isAssignableFrom(MemberRepository.class))
                               .findFirst()
                               .orElse(null);

        Assert.notNull(clazz, "MemberReposity class cannot be null");

        Method questionedMethod = Arrays.stream(clazz.getDeclaredMethods())
                                        .filter(method -> method.isAnnotationPresent(Question.class))
                                        .findFirst()
                                        .orElse(null);

        Assert.notNull(questionedMethod, "@Question annotated method cannot be null");

        List<Member> members = ReflectionTestUtils.invokeMethod(memberRepository, questionedMethod.getName(), "type1");

        return members.stream()
                      .map(Member::getDetails)
                      .flatMap(Collection::stream)
                      .map(MemberDetail::getDescription)
                      .collect(Collectors.toList());

    }

}

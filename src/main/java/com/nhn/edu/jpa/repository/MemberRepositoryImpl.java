package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Member;
import com.nhn.edu.jpa.entity.QMember;
import com.nhn.edu.jpa.entity.QMemberDetail;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MemberRepositoryImpl extends QuerydslRepositorySupport
        implements MemberRepositoryCustom {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public List<Member> getMembersWithAssociation() {
        QMember member = QMember.member;
        QMemberDetail memberDetail = QMemberDetail.memberDetail;

        return from(member).innerJoin(member.details, memberDetail).fetchJoin()
                           .fetch();
    }

}

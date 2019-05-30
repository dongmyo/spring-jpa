package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Member;
import com.nhn.edu.jpa.entity.QMember;
import com.nhn.edu.jpa.entity.QMemberDetail;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Member> getPagedMembersWithAssociations(Pageable pageable) {
        QMember member = QMember.member;
        QMemberDetail memberDetail = QMemberDetail.memberDetail;

        JPQLQuery<Long> query = from(member).select(member.memberId);

        List<Long> ids = getQuerydsl().applyPagination(pageable, query)
                                      .fetch();

        List<Member> list = from(member)
                .innerJoin(member.details, memberDetail).fetchJoin()
                .where(member.memberId.in(ids))
                .fetch();

        return new PageImpl<>(list, pageable, list.size());
    }

}

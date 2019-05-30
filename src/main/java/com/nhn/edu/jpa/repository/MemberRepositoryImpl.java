package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Member;
import com.nhn.edu.jpa.entity.QMember;
import com.nhn.edu.jpa.entity.QMemberDetail;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.NoResultException;
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

        // TODO #1 : pagination query - pagination 적용해서 ID만 추출.
        JPQLQuery<Long> query = null;
        JPQLQuery<Long> pagedQuery = getQuerydsl().applyPagination(pageable, query);

        long totalCount = 0L;
        try {
            totalCount = pagedQuery.fetchCount();
        } catch (NoResultException ex) {
            // ignore
        }

        List<Long> ids = pagedQuery.fetch();

        // TODO #2 : fetch join을 적용해서 연관관계에 있는 Entity까지 한꺼번에 조회.
        List<Member> list = null;

        return new PageImpl<>(list, pageable, totalCount);
    }

}

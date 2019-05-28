package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.MemberDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Long> {
}

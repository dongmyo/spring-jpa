package com.nhn.edu.jpa.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long memberId;

    private String name;

    @Column(name = "create_dt")
    private LocalDateTime createDate;

    @OneToMany
    @JoinColumn(name = "member_id")
    private List<MemberDetail> details = new ArrayList<>();

}

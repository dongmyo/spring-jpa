package com.nhn.edu.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "MemberDetails")
public class MemberDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_detail_id")
    private Long memberDetailId;

    private String type;

    private String description;

}

package com.nhn.edu.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "MemberDetails")
public class MemberDetail {
    @EmbeddedId
    private Pk pk;

    private String description;

    @ManyToOne
    @MapsId("memberId")
    private Member member;

    @Getter
    @Setter
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "member_id")
        private Long memberId;

        private String type;

    }

}

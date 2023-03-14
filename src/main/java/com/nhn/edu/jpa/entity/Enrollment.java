package com.nhn.edu.jpa.entity;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @Column(name = "enrolled_at")
    private LocalDateTime enrolledAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_id")
    private Course course;

    // TODO #2: 양방향 연관관계로 바꾸기
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}

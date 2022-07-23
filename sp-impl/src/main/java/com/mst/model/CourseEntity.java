package com.mst.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "course")
public class CourseEntity extends AbstractEntity {

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "courses")
    private Set<UserEntity> users;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private UniversityEntity university;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<PracticeWorkEntity> practiceWorks;
}

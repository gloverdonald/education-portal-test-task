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
@Table(name = "practice_work")
public class PracticeWorkEntity extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "general_info")
    private String generalInfo;

    @Column(name = "technical_requirements")
    private String technicalRequirements;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @OneToMany
    @JoinTable(
            name = "practice_work_files",
            joinColumns = @JoinColumn(name = "practice_work_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private Set<FileEntity> files;
}

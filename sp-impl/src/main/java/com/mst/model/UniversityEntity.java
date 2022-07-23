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
@Table(name = "university")
public class UniversityEntity extends AbstractEntity {

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "university_code")
    private String universityCode;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private Set<CourseEntity> courses;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private Set<UniversityYearEntity> universityYears;

}

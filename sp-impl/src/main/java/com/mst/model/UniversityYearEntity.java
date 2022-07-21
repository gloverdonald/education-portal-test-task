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
@Table(name = "university_year")
public class UniversityYearEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private UniversityEntity university;

    @Column(name = "year")
    private Long year;

    @OneToMany(mappedBy = "universityYear", fetch = FetchType.LAZY)
    private Set<AcademyGroupEntity> academyGroups;

}

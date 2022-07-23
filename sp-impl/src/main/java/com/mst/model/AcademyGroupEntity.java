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
@Table(name = "academy_group")
public class AcademyGroupEntity extends AbstractEntity {

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "academyGroup", fetch = FetchType.LAZY)
    private Set<UserEntity> users;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "university_year_id")
    private UniversityYearEntity universityYear;
}

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
@Table(name = "skill")
public class SkillEntity extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "skills")
    private Set<CVEntity> cvEntities;
}

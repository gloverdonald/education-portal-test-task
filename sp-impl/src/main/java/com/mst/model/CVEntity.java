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
@Table(name = "resume")
public class CVEntity extends AbstractEntity {

    @Column(name = "experience_desc")
    private String experienceDesc;

    @Column(name = "portfolio_links")
    private String portfolioLinks;

    @ManyToMany
    @JoinTable(
            name = "resume_skill",
            joinColumns = {@JoinColumn(name = "resume_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<SkillEntity> skills;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private FileEntity file;
}

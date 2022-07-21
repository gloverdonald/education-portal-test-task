package com.mst.model;

import com.mst.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "account")
public class UserEntity extends AbstractEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "telegram")
    private String telegram;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_date")
    private Date birthDate;

    @Builder.Default
    @Column(name = "verified")
    private Boolean verified = false;

    @Builder.Default
    @Column(name = "deleted")
    private Boolean deleted = false;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "confirm_code_id", referencedColumnName = "id")
    private ConfirmCodeEntity confirmCode;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private CVEntity cv;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<RefreshTokenEntity> refreshTokens;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "account_courses",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private Set<CourseEntity> courses;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "academy_group_id")
    private AcademyGroupEntity academyGroup;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private FileEntity avatar;
}

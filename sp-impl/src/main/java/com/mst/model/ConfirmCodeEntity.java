package com.mst.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "confirm_code")
public class ConfirmCodeEntity extends AbstractEntity {

    @Column(name = "code")
    private String confirmCode;

    @OneToOne(mappedBy = "confirmCode")
    private UserEntity user;
}

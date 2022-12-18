package ru.itis.polyclinictesttask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "confirm_code")
public class ConfirmCodeEntity extends AbstractEntity {

    @Column(name = "code")
    private String code;

    @OneToOne(mappedBy = "confirmCode")
    private AccountEntity account;
}
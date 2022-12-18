package ru.itis.polyclinictesttask.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.polyclinictesttask.model.enums.AccountType;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity extends AbstractEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hash_password")
    private String hashPassword;

    @Builder.Default
    @Column(name = "verified")
    private Boolean verified = false;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private FileEntity avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountType accountType;

    @OneToOne(mappedBy = "account", cascade = {CascadeType.ALL})
    private DoctorEntity doctor;

    @OneToOne(mappedBy = "account", cascade = {CascadeType.ALL})
    private PatientEntity patient;

    @OneToOne(mappedBy = "account", cascade = {CascadeType.ALL})
    private AdministratorEntity administratorEntity;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "confirm_code_id", referencedColumnName = "id")
    private ConfirmCodeEntity confirmCode;

    @ToString.Exclude
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<RefreshTokenEntity> tokens;
}
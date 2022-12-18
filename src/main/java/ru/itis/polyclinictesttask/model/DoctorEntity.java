package ru.itis.polyclinictesttask.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class DoctorEntity extends AbstractEntity {
    @Column(name = "education")
    private String education;

    @Column(name = "experience")
    private Integer experience;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    @ManyToMany
    @JoinTable(
            name = "doctor_medical_cares",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "medical_care_id")}
    )
    private List<MedicalCareEntity> medicalCare;
}

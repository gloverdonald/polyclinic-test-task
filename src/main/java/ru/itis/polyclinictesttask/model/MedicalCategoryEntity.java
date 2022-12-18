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
@Table(name = "medical_category")
public class MedicalCategoryEntity extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "medicalCategory", cascade = CascadeType.REMOVE)
    private List<MedicalCareEntity> medicalCareEntities;
}

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
@Table(name = "medical_care")
public class MedicalCareEntity extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "medical_category_id", nullable = false)
    private MedicalCategoryEntity medicalCategory;

    @ManyToMany(mappedBy = "medicalCare")
    private List<DoctorEntity> doctors;
}
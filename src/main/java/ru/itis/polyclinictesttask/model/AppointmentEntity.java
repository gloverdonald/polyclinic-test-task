package ru.itis.polyclinictesttask.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.polyclinictesttask.model.enums.AppointmentStatus;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class AppointmentEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus status;

    @OneToOne
    @JoinColumn(name = "medical_care_id", referencedColumnName = "id")
    private MedicalCareEntity medicalCare;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private DoctorEntity doctor;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "anamnesis")
    private String anamnesis;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @ElementCollection
    @CollectionTable(name = "link", joinColumns = @JoinColumn(name = "appointment_id"))
    @Column(name = "content")
    private Set<String> links;
}

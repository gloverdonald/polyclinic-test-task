package ru.itis.polyclinictesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.polyclinictesttask.model.PatientEntity;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {
}

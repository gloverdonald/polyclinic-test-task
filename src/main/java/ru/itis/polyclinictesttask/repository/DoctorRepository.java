package ru.itis.polyclinictesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itis.polyclinictesttask.model.DoctorEntity;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<DoctorEntity, UUID>, JpaSpecificationExecutor<DoctorEntity> {

}

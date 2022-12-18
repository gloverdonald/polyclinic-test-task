package ru.itis.polyclinictesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itis.polyclinictesttask.model.AppointmentEntity;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID>, JpaSpecificationExecutor<AppointmentEntity> {
}

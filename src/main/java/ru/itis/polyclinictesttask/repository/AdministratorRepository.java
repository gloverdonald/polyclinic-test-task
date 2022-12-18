package ru.itis.polyclinictesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.polyclinictesttask.model.AdministratorEntity;

import java.util.UUID;

public interface AdministratorRepository extends JpaRepository<AdministratorEntity, UUID> {
}

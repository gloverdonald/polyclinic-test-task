package ru.itis.polyclinictesttask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itis.polyclinictesttask.model.MedicalCategoryEntity;

import java.util.UUID;

public interface MedicalCategoryRepository extends JpaRepository<MedicalCategoryEntity, UUID>, JpaSpecificationExecutor<MedicalCategoryEntity> {
    Page<MedicalCategoryEntity> findAll(Pageable pageable);
}
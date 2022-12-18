package ru.itis.polyclinictesttask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itis.polyclinictesttask.model.MedicalCareEntity;

import java.util.UUID;

public interface MedicalCareRepository extends JpaRepository<MedicalCareEntity, UUID>, JpaSpecificationExecutor<MedicalCareEntity> {
    Page<MedicalCareEntity> findAllByMedicalCategoryId(UUID categoryId, Pageable of);
}

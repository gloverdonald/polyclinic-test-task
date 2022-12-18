package ru.itis.polyclinictesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.polyclinictesttask.model.ConfirmCodeEntity;

import java.util.Optional;
import java.util.UUID;

public interface ConfirmCodeRepository extends JpaRepository<ConfirmCodeEntity, UUID> {

    Optional<ConfirmCodeEntity> findByCode(String code);
}

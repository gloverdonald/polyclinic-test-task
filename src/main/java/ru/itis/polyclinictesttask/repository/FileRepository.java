package ru.itis.polyclinictesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.polyclinictesttask.model.FileEntity;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {

}

package ru.itis.polyclinictesttask.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.polyclinictesttask.dto.response.FileResponse;
import ru.itis.polyclinictesttask.model.FileEntity;

import java.util.UUID;

public interface FileService {

    FileEntity getById(UUID id);

    FileResponse getResponseById(UUID id);

    UUID save(MultipartFile file);
}

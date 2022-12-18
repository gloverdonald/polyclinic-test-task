package ru.itis.polyclinictesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.polyclinictesttask.dto.response.FileResponse;
import ru.itis.polyclinictesttask.exception.FileNotFoundException;
import ru.itis.polyclinictesttask.mapper.FileMapper;
import ru.itis.polyclinictesttask.model.FileEntity;
import ru.itis.polyclinictesttask.repository.FileRepository;
import ru.itis.polyclinictesttask.service.FileService;
import ru.itis.polyclinictesttask.service.FileStorageService;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileStorageService fileStorageService;

    private final FileMapper fileMapper;

    private final FileRepository fileRepository;

    @Override
    public UUID save(MultipartFile file) {
        return fileRepository.save(FileEntity.builder()
                .fileId(fileStorageService.save(file))
                .mimeType(file.getContentType())
                .originalFileName(file.getOriginalFilename())
                .size(file.getSize())
                .build()).getId();
    }

    @Override
    public FileEntity getById(UUID id) {
        return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public FileResponse getResponseById(UUID id) {
        FileEntity file = fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
        FileResponse fileResponse = fileMapper.toResponse(file);
        fileResponse.setBytes(fileStorageService.getById(file.getFileId()));
        return fileResponse;
    }
}

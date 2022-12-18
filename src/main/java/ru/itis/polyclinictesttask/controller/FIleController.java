package ru.itis.polyclinictesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.polyclinictesttask.api.FileApi;
import ru.itis.polyclinictesttask.dto.response.FileResponse;
import ru.itis.polyclinictesttask.service.FileService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FIleController implements FileApi {

    private final FileService fileService;

    @Override
    public ResponseEntity<UUID> uploadFile(MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fileService.save(file));
    }

    @Override
    public ResponseEntity<FileResponse> getFileInfo(UUID id) {
        return ResponseEntity.ok(fileService.getResponseById(id));
    }

    @Override
    public ResponseEntity<byte[]> showFile(UUID id) {
        FileResponse fileResponse = fileService.getResponseById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileResponse.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResponse.getName() + "\"")
                .body(fileResponse.getBytes());
    }
}
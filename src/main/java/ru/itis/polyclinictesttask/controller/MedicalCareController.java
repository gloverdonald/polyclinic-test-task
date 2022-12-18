package ru.itis.polyclinictesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.polyclinictesttask.api.MedicalCareApi;
import ru.itis.polyclinictesttask.dto.request.MedicalCareRequest;
import ru.itis.polyclinictesttask.dto.response.MedicalCareResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;
import ru.itis.polyclinictesttask.security.details.UserDetailsImpl;
import ru.itis.polyclinictesttask.service.MedicalCareService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MedicalCareController implements MedicalCareApi<UserDetailsImpl> {
    private final MedicalCareService medicalCareService;

    @Override
    public ResponseEntity<MedicalCareResponse> createMedicalCare(MedicalCareRequest medicalCategoryRequest) {
        return ResponseEntity.ok(medicalCareService.create(medicalCategoryRequest));
    }

    @Override
    public ResponseEntity<PageResponse<MedicalCareResponse>> getMedicalCare(UUID categoryId, Integer currentPage, Integer sizePage, String param, String order) {
        return ResponseEntity.ok(medicalCareService.getAll(categoryId, currentPage, sizePage, param, order));
    }


    @Override
    public ResponseEntity<MedicalCareResponse>  getMedicalCare(UUID id) {
        return ResponseEntity.ok(medicalCareService.getCareResponse(id));
    }
}

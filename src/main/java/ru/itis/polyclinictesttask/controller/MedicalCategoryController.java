package ru.itis.polyclinictesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.polyclinictesttask.api.MedicalCategoryApi;
import ru.itis.polyclinictesttask.dto.request.MedicalCategoryRequest;
import ru.itis.polyclinictesttask.dto.response.MedicalCategoryResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;
import ru.itis.polyclinictesttask.security.details.UserDetailsImpl;
import ru.itis.polyclinictesttask.service.MedicalCategoryService;

@RestController
@RequiredArgsConstructor
public class MedicalCategoryController implements MedicalCategoryApi<UserDetailsImpl> {

    private final MedicalCategoryService medicalCategoryService;

    @Override
    public ResponseEntity<MedicalCategoryResponse> createCategory(MedicalCategoryRequest medicalCategoryRequest) {
        return ResponseEntity.ok(medicalCategoryService.create(medicalCategoryRequest));
    }

    @Override
    public ResponseEntity<PageResponse<MedicalCategoryResponse>> getMedicalCategories(Integer currentPage, Integer sizePage, String param, String order, String searchValue) {
        return ResponseEntity.ok(medicalCategoryService.getAllMedicalCategories(currentPage, sizePage, param, order));
    }
}

package ru.itis.polyclinictesttask.service;

import ru.itis.polyclinictesttask.dto.request.MedicalCategoryRequest;
import ru.itis.polyclinictesttask.dto.response.MedicalCategoryResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;

public interface MedicalCategoryService {
    PageResponse<MedicalCategoryResponse> getAllMedicalCategories(Integer currentPage, Integer sizePage, String param, String order);

    MedicalCategoryResponse create(MedicalCategoryRequest medicalCategoryRequest);
}

package ru.itis.polyclinictesttask.service;

import ru.itis.polyclinictesttask.dto.request.MedicalCareRequest;
import ru.itis.polyclinictesttask.dto.response.MedicalCareResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;

import java.util.UUID;

public interface MedicalCareService {
    PageResponse<MedicalCareResponse> getAll(UUID categoryId, Integer currentPage, Integer sizePage, String param, String order);

    MedicalCareResponse create(MedicalCareRequest medicalCareRequest);


    MedicalCareResponse getCareResponse(UUID id);
}

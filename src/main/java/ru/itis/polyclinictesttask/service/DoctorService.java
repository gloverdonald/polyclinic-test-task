package ru.itis.polyclinictesttask.service;

import ru.itis.polyclinictesttask.dto.request.DoctorMedicalCareRequest;
import ru.itis.polyclinictesttask.dto.response.DoctorResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;

import java.util.UUID;

public interface DoctorService {
    PageResponse<DoctorResponse> getAllDoctors(UUID medicalCareId, UUID medicalCategoryId, Integer currentPage, Integer sizePage, String param, String order);

    DoctorResponse getDoctorResponse(UUID id);

    void addMedicalCare(DoctorMedicalCareRequest doctorMedicalCareRequest);
}

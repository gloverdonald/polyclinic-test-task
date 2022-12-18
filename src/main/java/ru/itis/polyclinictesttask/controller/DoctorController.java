package ru.itis.polyclinictesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.polyclinictesttask.api.DoctorApi;
import ru.itis.polyclinictesttask.dto.request.DoctorMedicalCareRequest;
import ru.itis.polyclinictesttask.dto.response.DoctorResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;
import ru.itis.polyclinictesttask.dto.response.SuccessResponse;
import ru.itis.polyclinictesttask.security.details.UserDetailsImpl;
import ru.itis.polyclinictesttask.service.DoctorService;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class DoctorController implements DoctorApi<UserDetailsImpl> {
    private final DoctorService doctorService;

    @Override
    public ResponseEntity<PageResponse<DoctorResponse>> getDoctors(UUID medicalCareId, UUID medicalCategoryId, Integer currentPage, Integer sizePage, String param, String order) {
        return ResponseEntity.ok(doctorService.getAllDoctors(medicalCareId, medicalCategoryId, currentPage, sizePage, param, order));
    }

    @Override
    public ResponseEntity<DoctorResponse> getDoctor(UUID id) {
        return ResponseEntity.ok(doctorService.getDoctorResponse(id));
    }

    @Override
    public ResponseEntity<SuccessResponse> addMedicalCare(DoctorMedicalCareRequest doctorMedicalCareRequest) {
        doctorService.addMedicalCare(doctorMedicalCareRequest);
        return ResponseEntity.ok(SuccessResponse
                .builder()
                .message("medical care was added")
                .data(Instant.now())
                .build()
        );
    }
}

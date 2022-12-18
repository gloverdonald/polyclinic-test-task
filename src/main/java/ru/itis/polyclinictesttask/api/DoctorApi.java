package ru.itis.polyclinictesttask.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.polyclinictesttask.dto.request.DoctorMedicalCareRequest;
import ru.itis.polyclinictesttask.dto.response.DoctorResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;
import ru.itis.polyclinictesttask.dto.response.SuccessResponse;

import java.util.UUID;

@RequestMapping("/api/v1/doctor")
public interface DoctorApi<PRINCIPAL extends UserDetails> {
    @GetMapping
    ResponseEntity<PageResponse<DoctorResponse>> getDoctors(@RequestParam(name = "care", required = false) UUID medicalCareId,
                                                            @RequestParam(name = "category", required = false) UUID medicalCategoryId,
                                                            @RequestParam(name = "page", required = false, defaultValue = "0") Integer currentPage,
                                                            @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizePage,
                                                            @RequestParam(name = "param", required = false, defaultValue = "id") String param,
                                                            @RequestParam(name = "order", required = false, defaultValue = "") String order);

    @GetMapping("/{id}")
    ResponseEntity<DoctorResponse> getDoctor(@PathVariable UUID id);

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_ADMINISTRATOR')")
    @PostMapping("/care")
    ResponseEntity<SuccessResponse> addMedicalCare(@RequestBody DoctorMedicalCareRequest doctorMedicalCareRequest);


}

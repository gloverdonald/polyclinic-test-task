package ru.itis.polyclinictesttask.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.polyclinictesttask.dto.request.MedicalCareRequest;
import ru.itis.polyclinictesttask.dto.response.MedicalCareResponse;
import ru.itis.polyclinictesttask.dto.response.MedicalCategoryResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;

import java.util.UUID;

@RequestMapping("/api/v1/care")
public interface MedicalCareApi<PRINCIPAL extends UserDetails> {

    @Operation(summary = "Create new appointment")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "New appointment",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MedicalCategoryResponse.class))})})
    @PostMapping
    ResponseEntity<MedicalCareResponse> createMedicalCare(@RequestBody MedicalCareRequest medicalCareRequest);

    @GetMapping()
    ResponseEntity<PageResponse<MedicalCareResponse>> getMedicalCare(@RequestParam(name = "category", required = false) UUID categoryId,
                                                                     @RequestParam(name = "page", required = false, defaultValue = "0") Integer currentPage,
                                                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizePage,
                                                                     @RequestParam(name = "param", required = false, defaultValue = "id") String param,
                                                                     @RequestParam(name = "order", required = false, defaultValue = "") String order);

    ;

    @GetMapping("/{id}")
    ResponseEntity<MedicalCareResponse> getMedicalCare(@PathVariable UUID id);
}

package ru.itis.polyclinictesttask.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.polyclinictesttask.dto.request.MedicalCategoryRequest;
import ru.itis.polyclinictesttask.dto.response.MedicalCategoryResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;

@RequestMapping("/api/v1/category")
public interface MedicalCategoryApi<PRINCIPAL extends UserDetails>  {

    @Operation(summary = "Create new category")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "New category",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MedicalCategoryResponse.class))})})
    @PostMapping
    ResponseEntity<MedicalCategoryResponse> createCategory(@RequestBody MedicalCategoryRequest medicalCategoryRequest);

    @GetMapping
    ResponseEntity<PageResponse<MedicalCategoryResponse>> getMedicalCategories(@RequestParam(name = "page", required = false, defaultValue = "0") Integer currentPage,
                                                                               @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizePage,
                                                                               @RequestParam(name = "param", required = false, defaultValue = "id") String param,
                                                                               @RequestParam(name = "order", required = false, defaultValue = "") String order,
                                                                               @RequestParam(name = "search", required = false, defaultValue = "") String searchValue);
}

package ru.itis.polyclinictesttask.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.polyclinictesttask.dto.request.AppointmentRequest;
import ru.itis.polyclinictesttask.dto.response.AppointmentResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;

import java.time.OffsetDateTime;
import java.util.UUID;

@RequestMapping("/api/v1/appointment")
public interface AppointmentApi<PRINCIPAL extends UserDetails, T> {
    @Operation(summary = "Create new appointment")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "New appointment",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class))})})
    @PostMapping
    ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest appointmentRequest);

    @Operation(summary = "Get appointment")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "appointment",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class))})})
    @GetMapping("/{id}")
    ResponseEntity<AppointmentResponse> getAppointment(@PathVariable UUID id);

    @Operation(summary = "Update appointment")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Updated appointment",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class))})})
    @PutMapping("/{id}")
    ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable UUID id, @RequestBody AppointmentRequest appointmentRequest);

    @Operation(summary = "Get all appointments")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "appointments",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class))}),})
    @GetMapping
    ResponseEntity<PageResponse<AppointmentResponse>> getAllAppointment(@RequestParam(name = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime start,
                                                                        @RequestParam(name = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime end,
                                                                        @RequestParam(name = "page", required = false, defaultValue = "0") Integer currentPage,
                                                                        @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizePage,
                                                                        @RequestParam(name = "param", required = false, defaultValue = "id") String param,
                                                                        @RequestParam(name = "order", required = false, defaultValue = "") String order);

}

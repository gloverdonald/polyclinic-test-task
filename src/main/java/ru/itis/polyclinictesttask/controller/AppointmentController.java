package ru.itis.polyclinictesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.polyclinictesttask.api.AppointmentApi;
import ru.itis.polyclinictesttask.dto.request.AppointmentRequest;
import ru.itis.polyclinictesttask.dto.response.AppointmentResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;
import ru.itis.polyclinictesttask.model.AppointmentEntity;
import ru.itis.polyclinictesttask.security.details.UserDetailsImpl;
import ru.itis.polyclinictesttask.service.AppointmentService;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AppointmentController implements AppointmentApi<UserDetailsImpl, AppointmentEntity> {

    private final AppointmentService appointmentService;

    @Override
    public ResponseEntity<AppointmentResponse> createAppointment(AppointmentRequest appointmentRequest) {
        return ResponseEntity.ok(appointmentService.create(appointmentRequest));
    }

    @Override
    public ResponseEntity<AppointmentResponse> getAppointment(UUID id) {
        return ResponseEntity.ok(appointmentService.getCareResponse(id));
    }

    @Override
    public ResponseEntity<AppointmentResponse> updateAppointment(UUID id, AppointmentRequest appointmentRequest) {
        return ResponseEntity.ok(appointmentService.update(id, appointmentRequest));
    }

    @Override
    public ResponseEntity<PageResponse<AppointmentResponse>> getAllAppointment(OffsetDateTime start,
                                                                               OffsetDateTime end,
                                                                               Integer currentPage,
                                                                               Integer sizePage,
                                                                               String param,
                                                                               String order) {
        return ResponseEntity.ok(appointmentService.getAllAppointment(start, end, currentPage, sizePage, param, order));
    }
}

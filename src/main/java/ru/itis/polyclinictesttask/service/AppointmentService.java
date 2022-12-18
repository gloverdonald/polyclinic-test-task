package ru.itis.polyclinictesttask.service;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.polyclinictesttask.dto.request.AppointmentRequest;
import ru.itis.polyclinictesttask.dto.response.AppointmentResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;
import ru.itis.polyclinictesttask.model.AppointmentEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface AppointmentService {
    AppointmentResponse create(AppointmentRequest appointmentRequest);

    AppointmentResponse getCareResponse(UUID id);

    AppointmentResponse update(UUID id, AppointmentRequest appointmentRequest);

    PageResponse<AppointmentResponse> getAllAppointment(OffsetDateTime start, OffsetDateTime end, Integer currentPage, Integer sizePage, String param, String order);
}

package ru.itis.polyclinictesttask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import ru.itis.polyclinictesttask.dto.request.AppointmentRequest;
import ru.itis.polyclinictesttask.dto.response.AppointmentResponse;
import ru.itis.polyclinictesttask.model.AppointmentEntity;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AppointmentMapper {
    @Mapping(target = "medicalCareId", source = "medicalCare.id")
    @Mapping(target = "doctorId", source = "doctor.id")
    @Mapping(target = "patientId", source = "patient.id")
    AppointmentResponse toResponse(AppointmentEntity appointmentEntity);

    List<AppointmentResponse> toResponse(List<AppointmentEntity> appointmentEntities);

    AppointmentEntity toEntity(AppointmentRequest appointmentRequest);

    void update(@MappingTarget AppointmentEntity appointmentEntity, AppointmentRequest appointmentRequest);
}

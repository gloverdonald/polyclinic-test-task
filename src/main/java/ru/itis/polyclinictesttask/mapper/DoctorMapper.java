package ru.itis.polyclinictesttask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import ru.itis.polyclinictesttask.dto.request.DoctorRequest;
import ru.itis.polyclinictesttask.dto.response.AppointmentResponse;
import ru.itis.polyclinictesttask.dto.response.DoctorResponse;
import ru.itis.polyclinictesttask.model.AppointmentEntity;
import ru.itis.polyclinictesttask.model.DoctorEntity;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DoctorMapper {
    @Mapping(target = "firstName", source = "account.firstName")
    @Mapping(target = "lastName", source = "account.lastName")
    @Mapping(target = "email", source = "account.email")
    DoctorResponse toResponse(DoctorEntity doctorEntity);

    List<DoctorResponse> toResponse(List<DoctorEntity> doctorEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DoctorEntity toEntity(DoctorRequest doctorRequest);

    void update(@MappingTarget DoctorEntity doctorEntity, DoctorRequest doctorRequest);
}
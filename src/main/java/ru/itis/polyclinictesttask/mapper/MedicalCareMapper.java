package ru.itis.polyclinictesttask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import ru.itis.polyclinictesttask.dto.request.MedicalCareRequest;
import ru.itis.polyclinictesttask.dto.response.MedicalCareResponse;
import ru.itis.polyclinictesttask.model.MedicalCareEntity;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MedicalCareMapper {
    @Mapping(target = "categoryId", source = "medicalCategory.id")
    MedicalCareResponse toResponse(MedicalCareEntity medicalCare);

    List<MedicalCareResponse> toResponse(List<MedicalCareEntity> medicalCareEntities);

    MedicalCareEntity toEntity(MedicalCareRequest medicalCareRequest);

    void update(@MappingTarget MedicalCareEntity medicalCareEntity, MedicalCareRequest medicalCareRequest);
}

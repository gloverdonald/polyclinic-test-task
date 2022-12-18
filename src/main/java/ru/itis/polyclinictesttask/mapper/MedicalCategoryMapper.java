package ru.itis.polyclinictesttask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import ru.itis.polyclinictesttask.dto.request.MedicalCategoryRequest;
import ru.itis.polyclinictesttask.dto.response.MedicalCategoryResponse;
import ru.itis.polyclinictesttask.model.MedicalCategoryEntity;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MedicalCategoryMapper {
    MedicalCategoryResponse toResponse(MedicalCategoryEntity medicalCategory);

    List<MedicalCategoryResponse> toResponse(List<MedicalCategoryEntity> medicalCategoryEntities);

    MedicalCategoryEntity toEntity(MedicalCategoryRequest medicalCategoryRequest);

    void update(@MappingTarget MedicalCategoryEntity medicalCategoryEntity, MedicalCategoryRequest medicalCategoryRequest);
}

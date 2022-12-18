package ru.itis.polyclinictesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.polyclinictesttask.dto.request.MedicalCategoryRequest;
import ru.itis.polyclinictesttask.dto.response.MedicalCategoryResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;
import ru.itis.polyclinictesttask.mapper.MedicalCategoryMapper;
import ru.itis.polyclinictesttask.model.MedicalCategoryEntity;
import ru.itis.polyclinictesttask.repository.MedicalCategoryRepository;
import ru.itis.polyclinictesttask.service.MedicalCategoryService;

@RequiredArgsConstructor
@Service
public class MedicalCategoryServiceImpl implements MedicalCategoryService {

    private final MedicalCategoryRepository medicalCategoryRepository;
    private final MedicalCategoryMapper medicalCategoryMapper;

    @Override
    public PageResponse<MedicalCategoryResponse> getAllMedicalCategories(Integer currentPage, Integer sizePage, String param, String order) {
        Sort sort = Sort.by(Sort.Direction.ASC, param);
        if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, param);
        }
        Page<MedicalCategoryEntity> medicalEntityPage = medicalCategoryRepository.findAll(PageRequest.of(currentPage, sizePage, sort));
        return PageResponse.<MedicalCategoryResponse>builder()
                .currentPage(currentPage)
                .totalPages(medicalEntityPage.getTotalPages())
                .totalElements(medicalEntityPage.getTotalElements())
                .responses(medicalCategoryMapper.toResponse(medicalEntityPage.getContent()))
                .build();
    }

    @Override
    public MedicalCategoryResponse create(MedicalCategoryRequest medicalCategoryRequest) {
        return medicalCategoryMapper.toResponse(medicalCategoryRepository.save(medicalCategoryMapper.toEntity(medicalCategoryRequest)));
    }
}

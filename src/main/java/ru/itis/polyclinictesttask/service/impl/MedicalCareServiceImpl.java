package ru.itis.polyclinictesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.polyclinictesttask.dto.request.MedicalCareRequest;
import ru.itis.polyclinictesttask.dto.response.MedicalCareResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;
import ru.itis.polyclinictesttask.exception.MedicalCareNotFound;
import ru.itis.polyclinictesttask.exception.MedicalCategoryNotFound;
import ru.itis.polyclinictesttask.mapper.MedicalCareMapper;
import ru.itis.polyclinictesttask.model.MedicalCareEntity;
import ru.itis.polyclinictesttask.model.MedicalCategoryEntity;
import ru.itis.polyclinictesttask.repository.MedicalCareRepository;
import ru.itis.polyclinictesttask.repository.MedicalCategoryRepository;
import ru.itis.polyclinictesttask.service.MedicalCareService;
import ru.itis.polyclinictesttask.service.MedicalCategoryService;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MedicalCareServiceImpl implements MedicalCareService {

    private final MedicalCategoryRepository medicalCategoryRepository;
    private final MedicalCareRepository medicalCareRepository;
    private final MedicalCareMapper medicalCareMapper;

    @Override
    public PageResponse<MedicalCareResponse> getAll(UUID categoryId, Integer currentPage, Integer sizePage, String param, String order) {
        Sort sort = Sort.by(Sort.Direction.ASC, param);
        if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, param);
        }
        Page<MedicalCareEntity> medicalCategoryPage;
        if (categoryId == null) {
            medicalCategoryPage = medicalCareRepository.findAll(PageRequest.of(currentPage, sizePage, sort));
        } else {
            medicalCategoryPage = medicalCareRepository.findAllByMedicalCategoryId(categoryId, PageRequest.of(currentPage, sizePage, sort));
        }
        return PageResponse.<MedicalCareResponse>builder()
                .currentPage(currentPage)
                .totalPages(medicalCategoryPage.getTotalPages())
                .totalElements(medicalCategoryPage.getTotalElements())
                .responses(medicalCareMapper.toResponse(medicalCategoryPage.getContent()))
                .build();
    }

    @Override
    public MedicalCareResponse create(MedicalCareRequest medicalCareRequest) {
        MedicalCategoryEntity medicalCategory = medicalCategoryRepository.findById(medicalCareRequest.getCategoryId()).orElseThrow(MedicalCategoryNotFound::new);
        MedicalCareEntity medicalCareEntity = medicalCareMapper.toEntity(medicalCareRequest);
        medicalCareEntity.setMedicalCategory(medicalCategory);
        return medicalCareMapper.toResponse(medicalCareRepository.save(medicalCareEntity));
    }

    @Override
    public MedicalCareResponse getCareResponse(UUID id) {
        return medicalCareMapper.toResponse(medicalCareRepository.findById(id).orElseThrow(MedicalCareNotFound::new));
    }
}

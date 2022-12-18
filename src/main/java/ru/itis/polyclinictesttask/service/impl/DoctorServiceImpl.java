package ru.itis.polyclinictesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itis.polyclinictesttask.dto.request.DoctorMedicalCareRequest;
import ru.itis.polyclinictesttask.dto.response.DoctorResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;
import ru.itis.polyclinictesttask.exception.DoctorNotFoundException;
import ru.itis.polyclinictesttask.exception.MedicalCareNotFound;
import ru.itis.polyclinictesttask.mapper.DoctorMapper;
import ru.itis.polyclinictesttask.model.DoctorEntity;
import ru.itis.polyclinictesttask.model.MedicalCareEntity;
import ru.itis.polyclinictesttask.model.MedicalCategoryEntity;
import ru.itis.polyclinictesttask.repository.DoctorRepository;
import ru.itis.polyclinictesttask.repository.MedicalCareRepository;
import ru.itis.polyclinictesttask.service.DoctorService;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    private final DoctorMapper doctorMapper;

    private final MedicalCareRepository medicalCareRepository;

    @Override
    public PageResponse<DoctorResponse> getAllDoctors(UUID medicalCareId, UUID medicalCategoryId, Integer currentPage, Integer sizePage, String param, String order) {
        Sort sort = Sort.by(Sort.Direction.ASC, param);
        if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, param);
        }
        Specification<DoctorEntity> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (medicalCareId != null) {
                Join<DoctorEntity, MedicalCareEntity> careJoin = root.join("medicalCare");
                predicates.add(criteriaBuilder.equal(careJoin.get("id"), medicalCareId));
            }
            if (medicalCategoryId != null) {
                Join<DoctorEntity, MedicalCareEntity> careJoin = root.join("medicalCare");
                Join<MedicalCareEntity, MedicalCategoryEntity> categoryJoin = careJoin.join("medicalCategory");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("id"), medicalCategoryId));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Page<DoctorEntity> doctorEntityPage = doctorRepository.findAll(specification, PageRequest.of(currentPage, sizePage, sort));

        return PageResponse.<DoctorResponse>builder()
                .currentPage(currentPage)
                .totalPages(doctorEntityPage.getTotalPages())
                .totalElements(doctorEntityPage.getTotalElements())
                .responses(doctorMapper.toResponse(doctorEntityPage.getContent()))
                .build();
    }

    @Override
    public DoctorResponse getDoctorResponse(UUID id) {
        return null;
    }

    @Override
    public void addMedicalCare(DoctorMedicalCareRequest doctorMedicalCareRequest) {
        DoctorEntity doctorEntity = doctorRepository.findById(doctorMedicalCareRequest.getDoctorId()).orElseThrow(DoctorNotFoundException::new);
/*
        doctorEntity.getMedicalCare().stream().if
*/
        doctorEntity.getMedicalCare().add(medicalCareRepository.findById(doctorMedicalCareRequest.getCareId()).orElseThrow(MedicalCareNotFound::new));
        doctorRepository.save(doctorEntity);
    }
}

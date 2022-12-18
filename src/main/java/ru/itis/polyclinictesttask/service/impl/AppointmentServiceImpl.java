package ru.itis.polyclinictesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itis.polyclinictesttask.dto.request.AppointmentRequest;
import ru.itis.polyclinictesttask.dto.response.AppointmentResponse;
import ru.itis.polyclinictesttask.dto.response.PageResponse;
import ru.itis.polyclinictesttask.exception.AppointmentNotFoundException;
import ru.itis.polyclinictesttask.exception.DoctorNotFoundException;
import ru.itis.polyclinictesttask.exception.MedicalCareNotFound;
import ru.itis.polyclinictesttask.exception.PatientNotFoundException;
import ru.itis.polyclinictesttask.mapper.AppointmentMapper;
import ru.itis.polyclinictesttask.model.AppointmentEntity;
import ru.itis.polyclinictesttask.model.DoctorEntity;
import ru.itis.polyclinictesttask.model.enums.AppointmentStatus;
import ru.itis.polyclinictesttask.repository.AppointmentRepository;
import ru.itis.polyclinictesttask.repository.DoctorRepository;
import ru.itis.polyclinictesttask.repository.MedicalCareRepository;
import ru.itis.polyclinictesttask.repository.PatientRepository;
import ru.itis.polyclinictesttask.service.AppointmentService;
import ru.itis.polyclinictesttask.utils.AppointmentNotificationUtil;

import javax.persistence.criteria.Predicate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentNotificationUtil appointmentNotificationUtil;

    private final AppointmentRepository appointmentRepository;

    private final DoctorRepository doctorRepository;

    private final MedicalCareRepository medicalCareRepository;

    private final PatientRepository patientRepository;

    private final AppointmentMapper appointmentMapper;


    @Override
    public AppointmentResponse create(AppointmentRequest appointmentRequest) {
        AppointmentEntity appointmentEntity = appointmentMapper.toEntity(appointmentRequest);
        appointmentEntity.setStatus(AppointmentStatus.FREE);
        if (appointmentRequest.getDoctorId() != null) {
            DoctorEntity doctor = doctorRepository.findById(appointmentRequest.getDoctorId()).orElseThrow(DoctorNotFoundException::new);
            appointmentNotificationUtil.sendAppointmentNotification(appointmentEntity, doctor.getAccount());
            appointmentEntity.setDoctor(doctor);
        }
        if (appointmentRequest.getPatientId() != null) {
            appointmentEntity.setStatus(AppointmentStatus.BUSY);
            appointmentEntity.setPatient(patientRepository.findById(appointmentRequest.getPatientId()).orElseThrow(PatientNotFoundException::new));
        }
        if (appointmentRequest.getMedicalCareId() != null) {
            appointmentEntity.setMedicalCare(medicalCareRepository.findById(appointmentRequest.getMedicalCareId()).orElseThrow(MedicalCareNotFound::new));
        }
        return appointmentMapper.toResponse(appointmentRepository.save(appointmentEntity));
    }

    @Override
    public AppointmentResponse getCareResponse(UUID id) {
        return appointmentMapper.toResponse(appointmentRepository.findById(id).orElseThrow(AppointmentNotFoundException::new));
    }

    @Override
    public AppointmentResponse update(UUID id, AppointmentRequest appointmentRequest) {
        AppointmentEntity appointmentEntity = appointmentRepository.findById(id).orElseThrow(AppointmentNotFoundException::new);
        appointmentMapper.update(appointmentEntity, appointmentRequest);
        return appointmentMapper.toResponse(appointmentRepository.save(appointmentEntity));
    }

    @Override
    public PageResponse<AppointmentResponse> getAllAppointment(OffsetDateTime start, OffsetDateTime end, Integer currentPage, Integer sizePage, String param, String order) {
        Sort sort = Sort.by(Sort.Direction.ASC, param);
        if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, param);
        }

        Specification<AppointmentEntity> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (start != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), start));
            }
            if (end != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime"), end));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        Page<AppointmentEntity> appointmentEntityPage = appointmentRepository.findAll(specification, PageRequest.of(currentPage, sizePage, sort));

        return PageResponse.<AppointmentResponse>builder()
                .currentPage(currentPage)
                .totalPages(appointmentEntityPage.getTotalPages())
                .totalElements(appointmentEntityPage.getTotalElements())
                .responses(appointmentMapper.toResponse(appointmentEntityPage.getContent()))
                .build();
    }
}

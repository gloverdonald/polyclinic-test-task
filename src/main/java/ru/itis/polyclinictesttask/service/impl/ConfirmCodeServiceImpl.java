package ru.itis.polyclinictesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.polyclinictesttask.exception.ConfirmCodeNotFoundException;
import ru.itis.polyclinictesttask.model.ConfirmCodeEntity;
import ru.itis.polyclinictesttask.repository.ConfirmCodeRepository;
import ru.itis.polyclinictesttask.service.ConfirmCodeService;

@RequiredArgsConstructor
@Service
public class ConfirmCodeServiceImpl implements ConfirmCodeService {
    private final ConfirmCodeRepository confirmCodeRepository;

    @Override
    public ConfirmCodeEntity getEntityByConfirmCode(String code) {
        return confirmCodeRepository.findByCode(code).orElseThrow(ConfirmCodeNotFoundException::new);
    }
}
package ru.itis.polyclinictesttask.service;

import ru.itis.polyclinictesttask.model.ConfirmCodeEntity;

public interface ConfirmCodeService {
    ConfirmCodeEntity getEntityByConfirmCode(String code);
}

package ru.itis.polyclinictesttask.dto.validation.validators;

import ru.itis.polyclinictesttask.dto.request.PasswordUpdateRequest;
import ru.itis.polyclinictesttask.dto.validation.annotations.SamePasswords;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class SamePasswordsValidator implements ConstraintValidator<SamePasswords, PasswordUpdateRequest> {

    @Override
    public boolean isValid(PasswordUpdateRequest request, ConstraintValidatorContext context) {
        if (Objects.isNull(request.getNewPassword()) || Objects.isNull(request.getRepeatPassword())) {
            return true;
        }
        return request.getNewPassword().equals(request.getRepeatPassword());
    }
}

package ru.itis.polyclinictesttask.dto.validation.validators;

import ru.itis.polyclinictesttask.dto.request.AppointmentRequest;
import ru.itis.polyclinictesttask.dto.validation.annotations.ValidAppointmentDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AppointmentDatesValidator implements ConstraintValidator<ValidAppointmentDates, AppointmentRequest> {
    @Override
    public void initialize(ValidAppointmentDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(AppointmentRequest appointmentRequest, ConstraintValidatorContext context) {
        if (appointmentRequest.getStartTime() == null || appointmentRequest.getEndTime() == null) {
            return false;
        } else return appointmentRequest.getStartTime().compareTo(appointmentRequest.getEndTime()) < 0;
    }
}

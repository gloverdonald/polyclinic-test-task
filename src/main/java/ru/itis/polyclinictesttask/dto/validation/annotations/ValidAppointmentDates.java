package ru.itis.polyclinictesttask.dto.validation.annotations;

import ru.itis.polyclinictesttask.dto.validation.validators.AppointmentDatesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AppointmentDatesValidator.class)
public @interface ValidAppointmentDates {
    String message() default "invalid date interval";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


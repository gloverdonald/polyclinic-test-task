package ru.itis.polyclinictesttask.dto.validation.annotations;

import ru.itis.polyclinictesttask.dto.validation.validators.CustomPhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomPhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default "Wrong Number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
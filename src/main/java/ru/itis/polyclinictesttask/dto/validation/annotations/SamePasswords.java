package ru.itis.polyclinictesttask.dto.validation.annotations;

import ru.itis.polyclinictesttask.dto.validation.validators.SamePasswordsValidator;

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
@Constraint(validatedBy = SamePasswordsValidator.class)
public @interface SamePasswords {

    String message() default "Not password not same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

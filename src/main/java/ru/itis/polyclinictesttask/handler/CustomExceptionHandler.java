package ru.itis.polyclinictesttask.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itis.polyclinictesttask.exception.HttpServiceException;
import ru.itis.polyclinictesttask.handler.dto.ServiceErrorResponse;
import ru.itis.polyclinictesttask.handler.dto.ValidationErrorResponse;
import ru.itis.polyclinictesttask.handler.dto.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpServiceException.class)
    public ResponseEntity<ServiceErrorResponse> handleException(HttpServiceException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ServiceErrorResponse.builder()
                        .message(exception.getMessage())
                        .exception(exception.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponse> handleException(MethodArgumentNotValidException e) {
        List<ValidationErrorResponse> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            ValidationErrorResponse errorDto = ValidationErrorResponse.builder()
                    .message(fieldError.getDefaultMessage())
                    .exception(fieldError.getObjectName())
                    .field(fieldError.getField())
                    .build();
            errors.add(errorDto);
        });
        e.getBindingResult().getGlobalErrors().forEach(objectError -> {
            ValidationErrorResponse errorDto = ValidationErrorResponse.builder()
                    .message(objectError.getDefaultMessage())
                    .exception(objectError.getObjectName())
                    .build();
            errors.add(errorDto);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ValidationExceptionResponse.builder()
                        .errors(errors)
                        .build());
    }
}
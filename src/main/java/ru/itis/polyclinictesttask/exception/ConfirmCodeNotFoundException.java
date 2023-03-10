package ru.itis.polyclinictesttask.exception;

import org.springframework.http.HttpStatus;

public class ConfirmCodeNotFoundException extends HttpServiceException {
    public ConfirmCodeNotFoundException() {
        super(HttpStatus.FORBIDDEN, "Confirm code not found");
    }
}
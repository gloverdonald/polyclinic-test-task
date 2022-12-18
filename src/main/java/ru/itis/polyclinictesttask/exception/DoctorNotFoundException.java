package ru.itis.polyclinictesttask.exception;

public class DoctorNotFoundException extends ModelNotFoundException {
    public DoctorNotFoundException() {
        super("doctor not found");
    }
}

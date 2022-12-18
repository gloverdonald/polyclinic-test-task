package ru.itis.polyclinictesttask.exception;

public class MedicalCareNotFound extends ModelNotFoundException {
    public MedicalCareNotFound() {
        super("medical care not found");
    }
}
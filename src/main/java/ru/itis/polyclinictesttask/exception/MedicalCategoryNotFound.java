package ru.itis.polyclinictesttask.exception;

public class MedicalCategoryNotFound extends ModelNotFoundException {
    public MedicalCategoryNotFound() {
        super("medical category not found");
    }
}
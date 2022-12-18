package ru.itis.polyclinictesttask.exception;

public class PatientNotFoundException extends ModelNotFoundException {
    public PatientNotFoundException() {
        super("patient not found");
    }
}

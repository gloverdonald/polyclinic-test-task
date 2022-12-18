package ru.itis.polyclinictesttask.exception;

public class AppointmentNotFoundException extends ModelNotFoundException {
    public AppointmentNotFoundException() {
        super("appointment not found");
    }
}

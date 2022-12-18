package ru.itis.polyclinictesttask.exception;

public class UserNotFoundException extends ModelNotFoundException {
    public UserNotFoundException() {
        super("user not found");
    }
}

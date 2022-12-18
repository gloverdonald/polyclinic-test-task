package ru.itis.polyclinictesttask.exception;

public class WrongOldPasswordException extends UnauthorizedException {
    public WrongOldPasswordException() {
        super("Wrong old password");
    }
}

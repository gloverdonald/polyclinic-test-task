package ru.itis.polyclinictesttask.exception;

public class AccountTypeNotRecognizeException extends RuntimeException {
    public AccountTypeNotRecognizeException() {
        super("account type not recognize");
    }
}
package ru.itis.polyclinictesttask.exception;

public class FileNotFoundException extends ModelNotFoundException {

    public FileNotFoundException() {
        super("File Not Found");
    }
}

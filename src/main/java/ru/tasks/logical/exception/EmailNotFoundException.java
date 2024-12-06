package ru.tasks.logical.exception;

public class EmailNotFoundException extends Exception {
    public EmailNotFoundException(String message) {
        super(message);
    }
}

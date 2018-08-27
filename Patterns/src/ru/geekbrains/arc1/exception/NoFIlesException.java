package ru.geekbrains.arc1.exception;

public class NoFIlesException extends RuntimeException {
    public NoFIlesException() {
    }

    public NoFIlesException(String message) {
        super(message);
    }
}

package ru.yandex.practicum.catsgram.exception;

public class PostIdConflictException extends RuntimeException {
    public PostIdConflictException(final String message) {
        super(message);
    }
}

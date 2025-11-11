package org.example.todoappneuefische.exceptions;

public class InvalidToDoException extends RuntimeException {
    public InvalidToDoException(String message) {
        super(message);
    }
}

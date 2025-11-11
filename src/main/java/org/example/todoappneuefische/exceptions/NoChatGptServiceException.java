package org.example.todoappneuefische.exceptions;

public class NoChatGptServiceException extends RuntimeException {
    public NoChatGptServiceException(String message) {
        super(message);
    }
}

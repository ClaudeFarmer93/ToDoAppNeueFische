package org.example.todoappneuefische.exceptions;

public record ErrorMessage(
        String message,
        String reason,
        int status
) {
}

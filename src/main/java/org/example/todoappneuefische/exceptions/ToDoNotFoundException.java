package org.example.todoappneuefische.exceptions;

public class ToDoNotFoundException extends RuntimeException {
    public ToDoNotFoundException(String id) {
        super("ToDo with id " + id + " not found");
    }
}

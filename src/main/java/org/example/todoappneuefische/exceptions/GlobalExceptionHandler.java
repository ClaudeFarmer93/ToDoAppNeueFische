package org.example.todoappneuefische.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ToDoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleToDoNotFoundException(ToDoNotFoundException e) {
        return new ErrorMessage(e.getMessage(),HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(InvalidToDoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleInvalidToDoException(InvalidToDoException e) {
        return new ErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST.getReasonPhrase(),HttpStatus.BAD_REQUEST.value());
    }

}

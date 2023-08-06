package com.kumojin.eventsmanager.handlers;

import com.kumojin.eventsmanager.exceptions.EventAlreadyExistsException;
import com.kumojin.eventsmanager.exceptions.ObjectValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception) {
        var response = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .errorSource(exception.getSource())
                .validationErrors(exception.getErrorMessages())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EventAlreadyExistsException.class)
    public ResponseEntity<ExceptionRepresentation> eventAlreadyExistsException(EventAlreadyExistsException exception) {
        var response = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionRepresentation> genericException(Exception exception) {
        var response = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

package com.kumojin.eventsmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EventAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID= 1L;

    public EventAlreadyExistsException(String message) {
        super(message);
    }
}

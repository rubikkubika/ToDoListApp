package ru.retsko.todolistapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.WebApplicationException;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnathorizatedException extends WebApplicationException {
    public UnathorizatedException(String message) {
        super(message);
    }
}

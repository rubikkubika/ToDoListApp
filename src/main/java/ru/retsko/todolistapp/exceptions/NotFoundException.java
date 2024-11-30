package ru.retsko.todolistapp.exceptions;

import javax.ws.rs.WebApplicationException;

public class NotFoundException extends WebApplicationException {
    public NotFoundException(String message) {
        super(message);
    }
}

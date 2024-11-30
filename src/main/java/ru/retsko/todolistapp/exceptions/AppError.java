package ru.retsko.todolistapp.exceptions;

import lombok.Data;

import javax.ws.rs.WebApplicationException;
import java.util.Date;

@Data
public class AppError extends WebApplicationException {
    private int status;
    private String message;
    private Date timestamp;

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }


}

package ru.retsko.todolistapp.model.dtos;

import lombok.Data;

@Data
public class ExceptionDto {
    private int responseCode;
    private final String message;

    public ExceptionDto(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }


}

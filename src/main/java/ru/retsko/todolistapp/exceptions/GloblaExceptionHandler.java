package ru.retsko.todolistapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.retsko.todolistapp.model.dtos.ExceptionDto;

@ControllerAdvice
public class GloblaExceptionHandler {
    @ExceptionHandler(UnathorizatedException.class)
    public ResponseEntity<?> handleUnathorizatedException(UnathorizatedException exception) {
        ExceptionDto exceptionDetails = new ExceptionDto(401, exception.getMessage());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParameterException(MissingServletRequestParameterException exception) {
        ExceptionDto exceptionDetails = new ExceptionDto(400, exception.getMessage());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundExecption(NotFoundException exception) {
        ExceptionDto exceptionDetails = new ExceptionDto(400, exception.getMessage());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        ExceptionDto exceptionDetails = new ExceptionDto(400, exception.getMessage());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}

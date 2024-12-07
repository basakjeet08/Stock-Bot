package dev.anirban.stockbot.exception;

import dev.anirban.stockbot.dto.response.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(Exception exception) {
        ResponseWrapper<Object> response = new ResponseWrapper<>(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmployeeAlreadyExists.class)
    public ResponseEntity<ResponseWrapper<Object>> handleEmployeeAlreadyExists(EmployeeAlreadyExists exception) {
        ResponseWrapper<Object> response = new ResponseWrapper<>(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
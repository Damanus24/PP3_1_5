package ru.kata.spring.security.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiControllerExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handlerException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "Пользователя с таким id не существует", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //NOT_FOUND - 404 статус
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handlerException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

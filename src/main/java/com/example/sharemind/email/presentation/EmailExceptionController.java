package com.example.sharemind.email.presentation;

import com.example.sharemind.email.exception.InvalidEmailException;
import com.example.sharemind.email.exception.InvalidEmailTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class EmailExceptionController {
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> catchInvalidEmailException(InvalidEmailException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidEmailTypeException.class)
    public ResponseEntity<String> catchInvalidEmailTypeException(InvalidEmailTypeException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

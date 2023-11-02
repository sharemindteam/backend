package com.example.sharemind.consult.presentation;


import com.example.sharemind.consult.exception.ConsultNotFoundException;
import com.example.sharemind.consult.exception.IncorrectPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ConsultExceptionHandler {
    @ExceptionHandler(ConsultNotFoundException.class)
    public ResponseEntity<String> catchConsultNotFoundException(ConsultNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> catchIncorrectPasswordException(IncorrectPasswordException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}

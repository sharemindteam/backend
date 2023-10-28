package com.example.sharemind.counselor.presentation;

import com.example.sharemind.counselor.exception.CounselorNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CounselorExceptionController {

    @ExceptionHandler(CounselorNotFoundException.class)
    public ResponseEntity<String> catchCounselorNotFoundException(CounselorNotFoundException e) {

        log.error(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

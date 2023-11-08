package com.example.sharemind.email.exception;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String email) {
        super("유효하지 않은 이메일 형식입니다." + email);
    }
}

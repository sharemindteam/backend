package com.example.sharemind.global.exception;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super("비밀번호가 올바르지 않습니다.");
    }
}

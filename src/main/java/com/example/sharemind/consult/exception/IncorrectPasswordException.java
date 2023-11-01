package com.example.sharemind.consult.exception;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super("비밀번호가 올바르지 않습니다.");
    }
}

package com.example.sharemind.consult.exception;

public class ConsultNotFoundException extends RuntimeException {
    public ConsultNotFoundException() {
        super("consult 정보를 찾을 수 없습니다.");
    }
}
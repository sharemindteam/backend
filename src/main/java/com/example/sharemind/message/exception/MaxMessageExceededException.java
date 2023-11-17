package com.example.sharemind.message.exception;

public class MaxMessageExceededException extends RuntimeException {
    public MaxMessageExceededException(int count) {
        super("메세지는 " + count + "번 이상 보낼 수 없습니다.");
    }
}

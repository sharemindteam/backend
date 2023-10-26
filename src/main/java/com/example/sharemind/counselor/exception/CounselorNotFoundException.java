package com.example.sharemind.counselor.exception;

public class CounselorNotFoundException extends RuntimeException {

    public CounselorNotFoundException(Long counselorId) {
        super("요청한 아이디에 해당하는 상담사 정보를 찾을 수 없습니다 : " + counselorId);
    }
}

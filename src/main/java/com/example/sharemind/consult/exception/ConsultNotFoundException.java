package com.example.sharemind.consult.exception;

import java.util.UUID;

public class ConsultNotFoundException extends RuntimeException {
    public ConsultNotFoundException(Long consultId) {
        super("consult 정보를 찾을 수 없습니다." + consultId);
    }

    public ConsultNotFoundException(UUID consultUuid) {
        super("consult 정보를 찾을 수 없습니다." + consultUuid);
    }
}

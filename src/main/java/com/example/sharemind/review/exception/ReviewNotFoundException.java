package com.example.sharemind.review.exception;

import java.util.UUID;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Long consultId) {
        super("review 정보를 찾을 수 없습니다." + consultId);
    }

    public ReviewNotFoundException(UUID reviewUuid) {
        super("review 정보를 찾을 수 없습니다." + reviewUuid);
    }
}

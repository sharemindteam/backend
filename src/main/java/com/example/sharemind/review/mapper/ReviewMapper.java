package com.example.sharemind.review.mapper;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.review.domain.Review;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review toEntity(Consult consult) {
        return Review.builder()
                .consult(consult)
                .reviewUuid(UUID.randomUUID())
                .isActivated(true)
                .build();
    }
}

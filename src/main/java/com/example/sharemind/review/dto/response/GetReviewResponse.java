package com.example.sharemind.review.dto.response;

import com.example.sharemind.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetReviewResponse {
    private final Integer rating;
    private final String comment;

    @Builder
    public GetReviewResponse(Integer rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public static GetReviewResponse from(Review review) {
        return GetReviewResponse.builder()
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }
}

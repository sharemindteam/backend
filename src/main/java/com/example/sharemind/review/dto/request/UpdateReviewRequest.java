package com.example.sharemind.review.dto.request;

import lombok.Getter;

@Getter
public class UpdateReviewRequest {
    private Integer rating;
    private String comment;
}

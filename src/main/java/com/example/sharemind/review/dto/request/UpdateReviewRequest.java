package com.example.sharemind.review.dto.request;

import java.util.UUID;
import lombok.Getter;

@Getter
public class UpdateReviewRequest {
    private UUID reviewUuid;
    private Integer rating;
    private String comment;
}

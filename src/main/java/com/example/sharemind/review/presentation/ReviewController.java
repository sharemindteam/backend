package com.example.sharemind.review.presentation;

import com.example.sharemind.review.application.ReviewService;
import com.example.sharemind.review.dto.request.UpdateReviewRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{reviewUuid}")
    public ResponseEntity<Void> updateReview(@PathVariable UUID reviewUuid,
                                             @RequestBody UpdateReviewRequest updateReviewRequest) {
        reviewService.updateReview(reviewUuid, updateReviewRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{reviewUuid}")
    public ResponseEntity<Void> getConsult(@PathVariable UUID reviewUuid) {
        reviewService.findReviewByReviewUuid(reviewUuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

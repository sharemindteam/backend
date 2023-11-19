package com.example.sharemind.review.presentation;

import com.example.sharemind.review.application.ReviewService;
import com.example.sharemind.review.dto.request.UpdateReviewRequest;
import com.example.sharemind.review.dto.response.GetReviewResponse;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PatchMapping()
    public ResponseEntity<Void> updateReview(@RequestBody UpdateReviewRequest updateReviewRequest) {
        reviewService.updateReview(updateReviewRequest.getReviewUuid(), updateReviewRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{reviewUuid}")
    public ResponseEntity<Void> getReview(@PathVariable UUID reviewUuid) {
        reviewService.findReviewByReviewUuid(reviewUuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("counselors/{counselorId}")
    public ResponseEntity<List<GetReviewResponse>> getReviewsByCounselorId(@PathVariable Long counselorId) {
        List<GetReviewResponse> reviews = reviewService.findAllReviewsByCounselorId(counselorId);
        return ResponseEntity.ok(reviews);
    }
}

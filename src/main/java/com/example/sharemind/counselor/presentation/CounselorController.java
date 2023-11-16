package com.example.sharemind.counselor.presentation;

import com.example.sharemind.counselor.application.CounselorService;
import com.example.sharemind.counselor.dto.response.CounselorResponse;
import com.example.sharemind.review.application.ReviewService;
import com.example.sharemind.review.dto.response.GetReviewResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/counselors")
@RequiredArgsConstructor
public class CounselorController {

    private final CounselorService counselorService;
    private final ReviewService reviewService;

    @GetMapping("/{counselorId}")
    public ResponseEntity<CounselorResponse> getCounselor(@PathVariable Long counselorId) {
        return ResponseEntity.ok(counselorService.getCounselor(counselorId));
    }

    @GetMapping("reviews/{counselorId}")
    public ResponseEntity<List<GetReviewResponse>> getReviewsByCounselorId(@PathVariable Long counselorId) {
        List<GetReviewResponse> reviews = reviewService.findAllReviewsByCounselorId(counselorId);
        return ResponseEntity.ok(reviews);
    }
}

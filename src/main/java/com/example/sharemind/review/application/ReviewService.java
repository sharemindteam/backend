package com.example.sharemind.review.application;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.review.domain.Review;
import com.example.sharemind.review.dto.request.UpdateReviewRequest;
import com.example.sharemind.review.dto.response.GetReviewResponse;
import java.util.List;
import java.util.UUID;

public interface ReviewService {
    void createReview(Consult consult);

    void updateReview(UUID reviewUuid, UpdateReviewRequest updateReviewRequest);

    List<GetReviewResponse> findAllReviewsByCounselorId(Long counselorId);

    void findReviewByReviewUuid(UUID reviewUuid);

    Review findReviewByConsult(Consult consult);
}

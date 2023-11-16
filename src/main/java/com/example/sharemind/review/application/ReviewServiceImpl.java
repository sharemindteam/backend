package com.example.sharemind.review.application;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.review.domain.Review;
import com.example.sharemind.review.dto.request.UpdateReviewRequest;
import com.example.sharemind.review.dto.response.GetReviewResponse;
import com.example.sharemind.review.exception.ReviewNotFoundException;
import com.example.sharemind.review.mapper.ReviewMapper;
import com.example.sharemind.review.repository.ReviewRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public void createReview(Consult consult) {
        reviewRepository.save(reviewMapper.toEntity(consult));
    }

    @Override
    public void updateReview(UUID reviewUuid, UpdateReviewRequest updateReviewRequest) {
        Review review = reviewRepository.findByReviewUuidAndIsActivated(reviewUuid, true)
                .orElseThrow(() -> new ReviewNotFoundException(reviewUuid));
        if (updateReviewRequest.getRating() != null) {
            review.updateRating(updateReviewRequest.getRating());
        }
        if (updateReviewRequest.getComment() != null) {
            review.updateComment(updateReviewRequest.getComment());
        }
        review.updateIsActivatedToFalse();
    }

    @Override
    public List<GetReviewResponse> findAllReviewsByCounselorId(Long counselorId) {
        List<Review> reviews = reviewRepository.findAllByCounselorId(counselorId);
        return reviews.stream()
                .map(GetReviewResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public void findReviewByReviewUuid(UUID reviewUuid) {
        reviewRepository.findByReviewUuidAndIsActivated(reviewUuid, true)
                .orElseThrow(() -> new ReviewNotFoundException(reviewUuid));
    }

    @Override
    public Review findReviewByConsult(Consult consult) {
        return reviewRepository.findByConsult(consult)
                .orElseThrow(() -> new ReviewNotFoundException(consult.getConsultId()));
    }
}

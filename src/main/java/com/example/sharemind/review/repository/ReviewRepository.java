package com.example.sharemind.review.repository;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.review.domain.Review;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Consult> {
    @Query("SELECT review FROM Review review WHERE review.consult.counselor.counselorId = :counselorId")
    List<Review> findAllByCounselorId(@Param("counselorId") Long counselorId);

    Optional<Review> findByReviewUuidAndIsActivated(UUID reviewUuid, Boolean isActivated);

    Optional<Review> findByConsult(Consult consult);
}

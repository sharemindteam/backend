package com.example.sharemind.review.domain;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Review extends BaseEntity {
    @Id
    @Column(name = "review_id")
    private Long reviewId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consult_id")
    private Consult consult;

    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private UUID reviewUuid;

    private Boolean isActivated;

    @Builder
    public Review(Consult consult, UUID reviewUuid, Boolean isActivated) {
        this.consult = consult;
        this.reviewUuid = reviewUuid;
        this.isActivated = isActivated;
    }

    public void updateIsActivatedToFalse() {
        this.isActivated = false;
    }

    public void updateRating(Integer newRating) {
        this.rating = newRating;
    }

    public void updateComment(String newComment) {
        this.comment = newComment;
    }
}

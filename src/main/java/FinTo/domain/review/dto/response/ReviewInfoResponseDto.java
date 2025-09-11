package FinTo.domain.review.dto.response;

import FinTo.domain.review.domain.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class ReviewInfoResponseDto {
    private Integer reviewCounts;
    private Double averageRatings;

    public static ReviewInfoResponseDto of(Integer reviewCounts, Double averageRatings) {
        return ReviewInfoResponseDto.builder()
                .reviewCounts(reviewCounts)
                .averageRatings(averageRatings)
                .build();
    }
}

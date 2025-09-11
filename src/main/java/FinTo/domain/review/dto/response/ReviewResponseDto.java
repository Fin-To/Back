package FinTo.domain.review.dto.response;

import FinTo.domain.review.domain.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class ReviewResponseDto {
    private Long reviewId;
    private Long memberId;
    private String profileImg;
    private String name;
    private LocalDate reviewedAt;
    private Integer rating;
    private String content;

    public static ResponseEntity<ReviewResponseDto> toResponseEntity(Review review){
        return ResponseEntity.ok(of(review));
    }

    public static ReviewResponseDto of(Review review){
        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
//                .profileImg(review.getMember().getProfileImg())
                .name(review.getMember().getName())
//                .reviewedAt(review.getCreatedAt())
                .rating(review.getRating())
                .content(review.getContent())
                .build();
    }
}

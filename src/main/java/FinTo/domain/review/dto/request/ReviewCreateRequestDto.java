package FinTo.domain.review.dto.request;


import FinTo.domain.review.domain.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCreateRequestDto {
    private String content;
    private Integer rating;

}

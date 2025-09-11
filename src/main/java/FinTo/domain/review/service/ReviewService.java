package FinTo.domain.review.service;

import FinTo.domain.review.dto.request.ReviewCreateRequestDto;
import FinTo.domain.review.dto.response.ReviewInfoResponseDto;
import FinTo.domain.review.dto.response.ReviewResponseDto;
import org.springframework.data.domain.Page;

public interface ReviewService {
    void createReview(Long mentoringId, ReviewCreateRequestDto reviewCreateRequestDto);
    ReviewInfoResponseDto getReviews(Long mentoringId);
    Page<ReviewResponseDto> getReviews(Long mentoringId, Integer pageNumber, Integer pageSize);
}

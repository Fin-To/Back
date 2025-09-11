package FinTo.domain.review.controller;

import FinTo.domain.member.dto.MemberResponseDto;
import FinTo.domain.review.domain.Review;
import FinTo.domain.review.dto.request.ReviewCreateRequestDto;
import FinTo.domain.review.dto.response.ReviewInfoResponseDto;
import FinTo.domain.review.dto.response.ReviewResponseDto;
import FinTo.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{mentoringId}")
    public ResponseEntity<?> createReview(@PathVariable Long mentoringId,
                                          @RequestBody ReviewCreateRequestDto reviewCreateRequestDto) {
        reviewService.createReview(mentoringId, reviewCreateRequestDto);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{mentoringId}/info")
    public ResponseEntity<?> getReview(@PathVariable Long mentoringId
    ){
        ReviewInfoResponseDto reviewInfo = reviewService.getReviews(mentoringId);
        return ResponseEntity.ok(reviewInfo);
    }

    @GetMapping("/{mentoringId}/list")
    public ResponseEntity<?> getReview(@PathVariable Long mentoringId,
                                       @RequestParam Integer pageNumber,
                                       @RequestParam Integer pageSize) {
        Page<ReviewResponseDto> reviews = reviewService.getReviews(mentoringId, pageNumber, pageSize);
        return ResponseEntity.ok(reviews);
    }
}

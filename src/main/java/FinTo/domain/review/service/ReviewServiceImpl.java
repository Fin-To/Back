package FinTo.domain.review.service;

import FinTo.domain.member.domain.Member;
import FinTo.domain.member.repository.MemberRepository;
import FinTo.domain.mentoring.domain.Mentoring;
import FinTo.domain.mentoring.repository.MentoringRepository;
import FinTo.domain.review.domain.Review;
import FinTo.domain.review.dto.request.ReviewCreateRequestDto;
import FinTo.domain.review.dto.response.ReviewInfoResponseDto;
import FinTo.domain.review.dto.response.ReviewResponseDto;
import FinTo.domain.review.repository.ReviewRepository;
import FinTo.global.security.jwt.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private final JwtUtil jwtUtil;

    private final MentoringRepository mentoringRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public void createReview(Long mentoringId, ReviewCreateRequestDto reviewCreateRequestDto){
        Long memberId = Long.valueOf(jwtUtil.getUserIdFromToken((String) SecurityContextHolder.getContext().getAuthentication().getCredentials()));
        log.info("userId : {}", memberId);

        Mentoring mentoring = mentoringRepository.findById(mentoringId)
                .orElseThrow(()-> new RuntimeException("멘토링 찾을 수 없음"));

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new RuntimeException("사용자를 찾을 수 없음"));

        Review review = Review.of(mentoring,member,reviewCreateRequestDto.getRating(),reviewCreateRequestDto.getContent());
        reviewRepository.save(review);
    }

    @Override
    public ReviewInfoResponseDto getReviews(Long mentoringId){
        List<Review> reviews = reviewRepository.findByMentoringId(mentoringId);

        int reviewCount = reviews.size();
        double averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
        return ReviewInfoResponseDto.of(reviewCount,averageRating);
    }

    @Override
    public Page<ReviewResponseDto> getReviews(Long mentoringId, Pageable pageable){
        Page<Review> reviewPage = reviewRepository.findByMentoringId(mentoringId, pageable);
        return reviewPage.map(ReviewResponseDto::of);
    }

}

package FinTo.domain.review.repository;

import FinTo.domain.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMentoringId(Long mentoringId);
    // 페이징용
    Page<Review> findByMentoringId(Long mentoringId, Pageable pageable);
}

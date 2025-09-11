package FinTo.domain.mentoring.repository;

import FinTo.domain.mentoring.domain.Mentoring;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentoringRepository extends JpaRepository<Mentoring, Long> {
    List<Mentoring> findByMentorId(Long mentorId);
}

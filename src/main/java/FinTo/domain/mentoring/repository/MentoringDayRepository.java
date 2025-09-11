package FinTo.domain.mentoring.repository;

import FinTo.domain.mentoring.domain.MentoringDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MentoringDayRepository extends JpaRepository<MentoringDay, Long> {
    List<MentoringDay> findByMentoringId(Long mentoringId);
}

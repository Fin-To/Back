package FinTo.domain.mentoring.repository;

import FinTo.domain.mentoring.domain.MentoringDay;
import FinTo.domain.mentoring.domain.MentoringDayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MentoringDayRepository extends JpaRepository<MentoringDay, Long> {
    List<MentoringDay> findByMentoring_Id(Long mentoringId);
    Optional<MentoringDay> findByMentoring_IdAndMentoringDay(Long mentoringId, MentoringDayOfWeek mentoringDayOfWeek);
}

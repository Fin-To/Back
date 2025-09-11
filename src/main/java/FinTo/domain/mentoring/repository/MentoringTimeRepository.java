package FinTo.domain.mentoring.repository;

import FinTo.domain.mentoring.domain.MentoringTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentoringTimeRepository extends JpaRepository<MentoringTime, Long> {
    List<MentoringTime> findByMentoringDay_Id(Long mentoringDayId);
}


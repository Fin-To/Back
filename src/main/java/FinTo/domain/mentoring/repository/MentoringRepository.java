package FinTo.domain.mentoring.repository;

import FinTo.domain.mentoring.domain.Mentoring;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MentoringRepository extends JpaRepository<Mentoring, Long> {
    Page<Mentoring> findByMentorId(Long mentorId, Pageable pageable);

    @Query("""
        SELECT m FROM Mentoring m
        JOIN FETCH m.mentor mentor
        JOIN FETCH mentor.member member
        LEFT JOIN FETCH member.nationality
        WHERE m.id = :mentoringId
        """)
    Optional<Mentoring> findByIdWithDetails(@Param("mentoringId") Long mentoringId);

    @Query("""
        SELECT COUNT(DISTINCT mm.member.id)
        FROM MentoringMeeting mm
        WHERE mm.mentoring.id = :mentoringId
        AND mm.status = 'APPROVED'
        """)
    Integer countMenteesByMentoringId(@Param("mentoringId") Long mentoringId);
}

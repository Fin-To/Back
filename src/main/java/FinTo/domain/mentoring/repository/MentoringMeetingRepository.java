package FinTo.domain.mentoring.repository;

import FinTo.domain.mentoring.domain.MentoringMeeting;
import FinTo.domain.mentoring.dto.response.MentoringMeetingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentoringMeetingRepository extends JpaRepository<MentoringMeeting, Long> {
    @Query("""
        SELECT new FinTo.domain.mentoring.dto.response.MentoringMeetingResponseDto(
            m.id,
            mt.title,
            m.status,
            mb.name,
            mb.email
        )
        FROM MentoringMeeting m
        JOIN m.mentoring mt
        JOIN mt.mentor mentor
        JOIN m.member mb
        WHERE mentor.member.id = :memberId
        """)
    Page<MentoringMeetingResponseDto> findMentoringMeetingInfoByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    @Query("""
        SELECT mm FROM MentoringMeeting mm
        JOIN FETCH mm.member
        WHERE mm.id = :meetingId
        """)
    Optional<MentoringMeeting> findByIdWithMember(@Param("meetingId") Long meetingId);

}

package FinTo.domain.mentoring.service;

import FinTo.domain.mentoring.dto.response.MentoringMeetingDetailResponse;
import FinTo.domain.mentoring.dto.response.MentoringMeetingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface MentoringMeetingService {
    Page<MentoringMeetingResponseDto> getMentoringMeetings(Pageable pageable);
    void applyMentoring(Long mentoringId, Long memberId, LocalDateTime bookDateTime);
    MentoringMeetingDetailResponse getMentoringApplication(Long meetingId);
}


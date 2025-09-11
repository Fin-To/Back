package FinTo.domain.mentoring.service;

import FinTo.domain.mentoring.dto.response.MentoringMeetingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MentoringMeetingService {
    Page<MentoringMeetingResponseDto> getMentoringMeetings(Pageable pageable);
}

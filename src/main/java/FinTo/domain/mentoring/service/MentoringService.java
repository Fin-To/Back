package FinTo.domain.mentoring.service;

import FinTo.domain.mentoring.dto.request.MentoringCreateRequestDto;
import FinTo.domain.mentoring.dto.response.MentoringCardResponseDto;
import FinTo.domain.mentoring.dto.response.MentoringMyListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MentoringService {
    void createMentoring(MentoringCreateRequestDto requestDto);
    Page<MentoringMyListResponseDto> getMyMentorings(Long mentorId, Pageable pageable);
    Page<MentoringCardResponseDto> getAllMentorings(Pageable pageable);
}

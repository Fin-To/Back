package FinTo.domain.mentoring.service;

import FinTo.domain.mentoring.dto.request.MentoringCreateRequestDto;
import FinTo.domain.mentoring.dto.request.MentoringSearchCondition;
import FinTo.domain.mentoring.dto.request.MentoringUpdateRequestDto;
import FinTo.domain.mentoring.dto.response.MentoringResponseDto;
import FinTo.domain.mentoring.dto.response.MentoringMyListResponseDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MentoringService {
    void createMentoring(MentoringCreateRequestDto requestDto);
    Page<MentoringMyListResponseDto> getMyMentorings(Long mentorId, Pageable pageable);
    Page<MentoringResponseDto> search(MentoringSearchCondition condition, Pageable pageable);
    void updateMentoring(Long mentoringId, MentoringUpdateRequestDto requestDto);
}

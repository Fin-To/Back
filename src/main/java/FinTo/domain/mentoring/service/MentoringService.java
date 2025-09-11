package FinTo.domain.mentoring.service;

import FinTo.domain.mentoring.dto.request.MentoringCreateRequestDto;


public interface MentoringService {
    void createMentoring(MentoringCreateRequestDto requestDto);
}

package FinTo.domain.mentor.service;

import FinTo.domain.mentor.dto.request.MentorUpgradeRequestDto;

public interface MentorService {
    void upgradeToMentor(MentorUpgradeRequestDto requestDto);
}

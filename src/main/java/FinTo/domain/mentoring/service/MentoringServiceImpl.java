package FinTo.domain.mentoring.service;

import FinTo.domain.mentor.domain.Mentor;
import FinTo.domain.mentor.repository.MentorRepository;
import FinTo.domain.mentoring.domain.Mentoring;
import FinTo.domain.mentoring.domain.MentoringDay;
import FinTo.domain.mentoring.domain.MentoringTime;
import FinTo.domain.mentoring.dto.request.MentoringCreateRequestDto;
import FinTo.domain.mentoring.dto.response.MentoringCardResponseDto;
import FinTo.domain.mentoring.dto.response.MentoringMyListResponseDto;
import FinTo.domain.mentoring.repository.MentoringDayRepository;
import FinTo.domain.mentoring.repository.MentoringRepository;
import FinTo.domain.mentoring.repository.MentoringTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService {

    private final MentorRepository mentorRepository;
    private final MentoringRepository mentoringRepository;
    private final MentoringDayRepository mentoringDayRepository;
    private final MentoringTimeRepository mentoringTimeRepository;

    @Transactional
    @Override
    public void createMentoring(MentoringCreateRequestDto requestDto) {
        Mentor mentor = mentorRepository.findById(requestDto.getMentorId())
                .orElseThrow(() -> new IllegalArgumentException("멘토 없음"));

        Mentoring mentoring = requestDto.toEntity(mentor);
        mentoringRepository.save(mentoring);

        requestDto.getDays().forEach(dayDto -> {
            MentoringDay day = dayDto.toEntity(mentoring);
            mentoringDayRepository.save(day);

            dayDto.getTimes().forEach(time -> {
                MentoringTime mentoringTime = MentoringTime.builder()
                        .mentoringDay(day)
                        .startTime(time)
                        .build();
                mentoringTimeRepository.save(mentoringTime);
            });
        });
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MentoringMyListResponseDto> getMyMentorings(Long mentorId, Pageable pageable) {
        return mentoringRepository.findByMentorId(mentorId, pageable)
                .map(MentoringMyListResponseDto::fromEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MentoringCardResponseDto> getAllMentorings(Pageable pageable) {
        return mentoringRepository.findAll(pageable)
                .map(MentoringCardResponseDto::fromEntity);
    }
}

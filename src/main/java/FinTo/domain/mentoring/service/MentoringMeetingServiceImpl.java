package FinTo.domain.mentoring.service;

import FinTo.domain.calendar.service.CalendarService;
import FinTo.domain.member.domain.Member;
import FinTo.domain.member.repository.MemberRepository;
import FinTo.domain.mentoring.domain.Mentoring;
import FinTo.domain.mentoring.domain.MentoringMeeting;
import FinTo.domain.mentoring.dto.response.MentoringMeetingResponseDto;
import FinTo.domain.mentoring.repository.MentoringMeetingRepository;
import FinTo.global.security.jwt.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import FinTo.domain.mentoring.domain.MeetingStatus;


import java.time.LocalDateTime;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class MentoringMeetingServiceImpl implements MentoringMeetingService {
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final MentoringMeetingRepository mentoringMeetingRepository;
    private final CalendarService calendarService;

    @Override
    @Transactional
    public Page<MentoringMeetingResponseDto> getMentoringMeetings(Pageable pageable){
        try{
            Long memberId = Long.valueOf(jwtUtil.getUserIdFromToken((String) SecurityContextHolder.getContext().getAuthentication().getCredentials()));
            log.info("userId : {}", memberId);

            return mentoringMeetingRepository.findMentoringMeetingInfoByMemberId(memberId, pageable);
        }
        catch (Exception e){
            log.error("멘토링 미팅 정보 조회 실패", e);
            throw new RuntimeException("멘토링 미팅 정보를 조회할 수 없습니다.", e);
        }
    }

    @Transactional
    @Override
    public void applyMentoring(Long mentoringId, Long memberId, LocalDateTime bookDatetime) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        MentoringMeeting meeting = MentoringMeeting.builder()
                .mentoring(Mentoring.builder().id(mentoringId).build())
                .member(member)
                .status(MeetingStatus.PENDING) // 신청 직후 "대기"
                .welcome("신청 완료되었습니다.")
                .build();

        mentoringMeetingRepository.save(meeting);

        // calendar 저장
        calendarService.saveCalendar(meeting, bookDatetime);
    }


}

package FinTo.domain.mentoring.service;

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


@Service
@Slf4j
@RequiredArgsConstructor
public class MentoringMeetingServiceImpl implements MentoringMeetingService {
    private final JwtUtil jwtUtil;
    private final MentoringMeetingRepository mentoringMeetingRepository;

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

}

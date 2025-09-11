package FinTo.domain.mentor.service;

import FinTo.domain.member.domain.Member;
import FinTo.domain.member.domain.MemberRole;
import FinTo.domain.member.repository.MemberRepository;
import FinTo.domain.mentor.domain.Mentor;
import FinTo.domain.mentor.dto.request.MentorUpgradeRequestDto;
import FinTo.domain.mentor.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MemberRepository memberRepository;
    private final MentorRepository mentorRepository;

    @Transactional
    @Override
    public void upgradeToMentor(MentorUpgradeRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (member.getRole() == MemberRole.MENTOR) {
            throw new IllegalStateException("이미 멘토로 등록된 회원입니다.");
        }

        if (mentorRepository.existsByMemberId(member.getId())) {
            throw new IllegalStateException("이미 멘토 테이블에 존재합니다.");
        }

        member.setRole(MemberRole.MENTOR);

        Mentor mentor = Mentor.builder()
                .member(member)
                .introduce(requestDto.getIntroduce())
                .build();

        mentorRepository.save(mentor);
    }
}

package FinTo.domain.member.service;

import FinTo.domain.member.domain.OAuthProvider;
import FinTo.domain.member.dto.MemberCreateRequestDto;
import FinTo.domain.member.dto.MemberUpdateRequestDto;
import FinTo.domain.member.repository.MemberRepository;
import FinTo.domain.member.domain.Member;
import FinTo.domain.member.exception.MemberNotFoundException;
import FinTo.domain.nationality.Nationality;
import FinTo.domain.nationality.NationalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final NationalityService nationalityService;

    @Override
    public Member create(MemberCreateRequestDto dto) {
        return memberRepository.save(dto.toEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public Member getById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    @Transactional
    public Member getOrCreateByOAuthInfo(OAuthProvider provider, String oAuthId, String email) {
        return memberRepository.findByOauthProviderAndOauthId(provider, oAuthId)
                .orElseGet(() -> create(MemberCreateRequestDto.of(null, provider, oAuthId, email)));
    }

    @Override
    public void update(Long memberId, MemberUpdateRequestDto dto) {
        Member member = getById(memberId);

        Optional.ofNullable(dto.getName()).ifPresent(member::setName);
        Optional.ofNullable(dto.getBirthDate()).ifPresent(member::setBirthDate);
        Optional.ofNullable(dto.getGender()).ifPresent(member::setGender);
        Optional.ofNullable(dto.getNationalityId()).ifPresent(nationalityId -> {
            Nationality nationality = nationalityService.getById(nationalityId);
            member.setNationality(nationality);
        });
    }

    @Override
    public void delete(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}

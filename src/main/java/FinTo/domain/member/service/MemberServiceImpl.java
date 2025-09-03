package FinTo.domain.member.service;

import FinTo.domain.member.domain.OAuthProvider;
import FinTo.domain.member.dto.MemberCreateRequestDto;
import FinTo.domain.member.repository.MemberRepository;
import FinTo.domain.member.domain.Member;
import FinTo.domain.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member create(MemberCreateRequestDto requestDto) {
        return memberRepository.save(requestDto.toEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public Member getById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Member getOrCreateByOAuthInfo(OAuthProvider provider, String oAuthId) {
        return memberRepository.findByOauthProviderAndOauthId(provider, oAuthId)
                .orElseGet(() -> create(MemberCreateRequestDto.of(null, provider, oAuthId)));
    }
}

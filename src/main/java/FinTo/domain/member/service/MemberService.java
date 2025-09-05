package FinTo.domain.member.service;

import FinTo.domain.member.dto.MemberCreateRequestDto;
import FinTo.domain.member.domain.Member;
import FinTo.domain.member.domain.OAuthProvider;
import FinTo.domain.member.dto.MemberUpdateRequestDto;

public interface MemberService {
    Member create(MemberCreateRequestDto requestDto);
    Member getById(Long userId);
    Member getOrCreateByOAuthInfo(OAuthProvider provider, String oAuthId);
    void update(Long userId, MemberUpdateRequestDto requestDto);
    void delete(Long userId);
}

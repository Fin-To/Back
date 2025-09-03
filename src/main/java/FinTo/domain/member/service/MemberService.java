package FinTo.domain.member.service;

import FinTo.domain.member.dto.MemberCreateRequestDto;
import FinTo.domain.member.domain.Member;
import FinTo.domain.member.domain.OAuthProvider;

public interface MemberService {
    Member create(MemberCreateRequestDto requestDto);
    Member getById(Long id);
    Member getOrCreateByOAuthInfo(OAuthProvider provider, String oAuthId);
}

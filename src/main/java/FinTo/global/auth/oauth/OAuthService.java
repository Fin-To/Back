package FinTo.global.auth.oauth;

import FinTo.domain.member.domain.OAuthProvider;
import FinTo.global.auth.dto.OAuthInfoResponseDto;

public interface OAuthService {
    OAuthProvider getProvider();
    OAuthInfoResponseDto getOAuthId(String code);
    String getAuthorizationUrl();
}

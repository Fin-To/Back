package FinTo.global.auth.oauth;

import FinTo.domain.member.domain.OAuthProvider;

public interface OAuthService {
    OAuthProvider getProvider();
    String getOAuthId(String code);
}

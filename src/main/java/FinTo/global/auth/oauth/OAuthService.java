package FinTo.global.auth.oauth;

import FinTo.domain.member.domain.OAuthProvider;
import FinTo.global.auth.dto.LoginResponseDto;
import org.springframework.http.ResponseEntity;

public interface OAuthService {
    OAuthProvider getProvider();
    ResponseEntity<LoginResponseDto> login(String code);
}

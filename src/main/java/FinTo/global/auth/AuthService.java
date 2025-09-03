package FinTo.global.auth;

import FinTo.domain.member.domain.Member;
import FinTo.domain.member.domain.OAuthProvider;
import FinTo.domain.member.service.MemberService;
import FinTo.global.auth.dto.LoginResponseDto;
import FinTo.global.auth.oauth.OAuthServiceFactory;
import FinTo.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OAuthServiceFactory oAuthServiceFactory;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseDto login(HttpServletResponse response, OAuthProvider provider, String code) {
        String oAuthId = oAuthServiceFactory.get(provider).getOAuthId(code);

        Member member = memberService.getOrCreateByOAuthInfo(provider, oAuthId);

        String accessToken = jwtTokenProvider.createAccessToken(member.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());
        storeRefreshTokenInCookie(response, refreshToken);

        return LoginResponseDto.of(accessToken);
    }

    private void storeRefreshTokenInCookie(HttpServletResponse response, String refreshToken) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setMaxAge(jwtTokenProvider.getRefreshTokenMaxAgeInSeconds());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}

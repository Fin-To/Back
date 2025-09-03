package FinTo.global.auth;

import FinTo.domain.member.domain.OAuthProvider;
import FinTo.global.auth.dto.LoginRequestDto;
import FinTo.global.auth.dto.LoginResponseDto;
import FinTo.global.auth.dto.OAuthUrlResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // OAuth 로그인 URL 반환
    @GetMapping("/login/{provider}/url")
    public ResponseEntity<OAuthUrlResponseDto> getOAuthLoginUrl(@PathVariable("provider") String provider) {
        OAuthProvider oAuthProvider = OAuthProvider.valueOf(provider.toUpperCase());
        String url = authService.getAuthorizationUrl(oAuthProvider);
        return ResponseEntity.ok(new OAuthUrlResponseDto(url));
    }

    @PostMapping("/login/{provider}")
    public ResponseEntity<LoginResponseDto> login(
            @PathVariable("provider") String provider,
            @RequestBody @Validated LoginRequestDto loginRequestDto,
            HttpServletResponse response
            ) {
        OAuthProvider oAuthProvider = OAuthProvider.valueOf(provider.toUpperCase());
        return ResponseEntity.ok(authService.login(response, oAuthProvider, loginRequestDto.getCode()));
    }
}

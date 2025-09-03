package FinTo.global.auth;

import FinTo.domain.member.domain.OAuthProvider;
import FinTo.global.auth.dto.LoginRequestDto;
import FinTo.global.auth.dto.LoginResponseDto;
import FinTo.global.auth.oauth.OAuthServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final OAuthServiceFactory oAuthServiceFactory;

    @PostMapping("/login/{provider}")
    public ResponseEntity<LoginResponseDto> login(
            @PathVariable("provider") String provider,
            @RequestBody LoginRequestDto loginRequestDto
            ) {
        OAuthProvider oAuthProvider = OAuthProvider.valueOf(provider.toUpperCase());
        return oAuthServiceFactory.get(oAuthProvider).login(loginRequestDto.getCode());
    }
}

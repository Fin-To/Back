package FinTo.global.auth;

import FinTo.domain.member.domain.OAuthProvider;
import FinTo.global.auth.dto.LoginRequestDto;
import FinTo.global.auth.dto.LoginResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/{provider}")
    public ResponseEntity<LoginResponseDto> login(
            @PathVariable("provider") String provider,
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletResponse response
            ) {
        OAuthProvider oAuthProvider = OAuthProvider.valueOf(provider.toUpperCase());
        return ResponseEntity.ok(authService.login(response, oAuthProvider, loginRequestDto.getCode()));
    }
}

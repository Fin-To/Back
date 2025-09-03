package FinTo.global.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponseDto {
    private String accessToken;

    public static LoginResponseDto of(String accessToken) {
        return new LoginResponseDto(accessToken);
    }
}

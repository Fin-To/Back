package FinTo.global.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthInfoResponseDto {
    private String oAuthId;
    private String email;

    public static OAuthInfoResponseDto of(String oAuthId, String email) {
        return new OAuthInfoResponseDto(oAuthId, email);
    }
}

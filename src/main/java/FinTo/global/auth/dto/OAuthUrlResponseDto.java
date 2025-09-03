package FinTo.global.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OAuthUrlResponseDto {
    String url;

    public static OAuthUrlResponseDto of(String url) {
        return new OAuthUrlResponseDto(url);
    }
}

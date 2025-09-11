package FinTo.domain.member.dto;

import FinTo.domain.member.domain.Member;
import FinTo.domain.member.domain.OAuthProvider;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class MemberCreateRequestDto {
    private String name;
    private OAuthProvider oauthProvider;
    private String oauthId;
    private String email;

    public static MemberCreateRequestDto of(String name, OAuthProvider provider, String oAuthId, String email) {
        return MemberCreateRequestDto.builder()
                .name(name)
                .oauthProvider(provider)
                .oauthId(oAuthId)
                .email(email)
                .build();
    }

    public Member toEntity() {
        return Member.of(
                name != null ? name : "익명",
                oauthProvider,
                oauthId,
                email
        );
    }
}

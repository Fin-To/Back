package FinTo.domain.member.domain;

import FinTo.domain.member.dto.MemberCreateRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Table(
        name = "member",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"oauth_provider", "oauth_id"})
        }
)
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private MemberRole role;
    private String name;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oauthProvider;
    private String oauthId;

    public static Member of(String name, OAuthProvider oauthProvider, String oauthId) {
        return Member.builder()
                .role(MemberRole.MEMBER)
                .name(name)
                .oauthProvider(oauthProvider)
                .oauthId(oauthId)
                .build();
    }
}

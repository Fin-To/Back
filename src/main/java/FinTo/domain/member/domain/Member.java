package FinTo.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private MemberRole role;
    private String name;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;
    private String oAuthId;

    public static Member create(String name) {
        return Member.builder()
                .role(MemberRole.MEMBER)
                .name(name)
                .build();
    }
}

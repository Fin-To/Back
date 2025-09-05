package FinTo.domain.member.domain;

import FinTo.domain.nationality.Nationality;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    private OAuthProvider oauthProvider;
    private String oauthId;

    private String name;
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_id")
    private Nationality nationality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public static Member of(String name, OAuthProvider oauthProvider, String oauthId) {
        return Member.builder()
                .role(MemberRole.MEMBER)
                .name(name)
                .oauthProvider(oauthProvider)
                .oauthId(oauthId)
                .build();
    }
}

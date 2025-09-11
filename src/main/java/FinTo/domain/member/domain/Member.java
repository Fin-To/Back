package FinTo.domain.member.domain;

import FinTo.domain.language.domain.MemberLanguage;
import FinTo.domain.nationality.domain.Nationality;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private String email;

    private String name;
    private LocalDate birthDate;

    private String profileImg;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberLanguage> memberLanguages = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Nationality nationality;


    public static Member of(String name, OAuthProvider oauthProvider, String oauthId, String email) {
        return Member.builder()
                .role(MemberRole.MEMBER)
                .name(name)
                .oauthProvider(oauthProvider)
                .oauthId(oauthId)
                .email(email)
                .build();
    }
}

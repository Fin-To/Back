package FinTo.domain.language.domain;

import FinTo.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MemberLanguage {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;
}

package FinTo.domain.nationality.domain;

import FinTo.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nationality")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Nationality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nationality_id")
    private Long id;

    @Column(length = 32, nullable = false)
    private String name; // 국가 이름

    @Column(length = 8, nullable = false)
    private String code; // ISO 국가 코드

    @Column(length = 32, nullable = false)
    private String emoji; // 국기 이모지

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member;
}

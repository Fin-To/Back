package FinTo.domain.review.domain;

import FinTo.domain.member.domain.Member;
import FinTo.domain.member.domain.OAuthProvider;
import FinTo.domain.mentoring.domain.Mentoring;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentoring_id", nullable = false)
    private Mentoring mentoring;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 255)
    private String content;

    @Column
    private LocalDateTime createdAt;

    @Builder
    public Review(Mentoring mentoring, Member member, int rating, String content) {
        this.mentoring = mentoring;
        this.member = member;
        this.rating = rating;
        this.content = content;
    }

    public static Review of(Mentoring mentoring, Member member, Integer rating, String content) {
        return Review.builder()
                .mentoring(mentoring)
                .member(member)
                .rating(rating)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

    }

}

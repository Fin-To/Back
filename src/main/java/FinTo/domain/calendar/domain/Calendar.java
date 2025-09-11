package FinTo.domain.calendar.domain;

import FinTo.domain.mentoring.domain.MentoringMeeting;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "calendar")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", nullable = false)
    private MentoringMeeting meeting;

    @Column(name = "book_datetime", nullable = false)
    private LocalDateTime bookDatetime;
}

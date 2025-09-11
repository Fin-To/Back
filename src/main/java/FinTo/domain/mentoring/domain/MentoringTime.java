package FinTo.domain.mentoring.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mentoring_time")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MentoringTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentoring_time_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentoring_day_id", nullable = false)
    private MentoringDay mentoringDay;

    @Column(name = "start_time", nullable = false)
    private int startTime;
}


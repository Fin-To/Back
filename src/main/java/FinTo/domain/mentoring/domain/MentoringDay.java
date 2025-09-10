package FinTo.domain.mentoring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mentoring_day")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MentoringDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "mentoring_day_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentoring_id", nullable = false)
    private Mentoring mentoring;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MentoringDayOfWeek day;

    @OneToMany(mappedBy = "mentoringDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MentoringTime> times = new ArrayList<>();
}


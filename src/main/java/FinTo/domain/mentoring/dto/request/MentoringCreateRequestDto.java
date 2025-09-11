package FinTo.domain.mentoring.dto.request;

import FinTo.domain.mentor.domain.Mentor;
import FinTo.domain.mentoring.domain.Mentoring;
import FinTo.domain.mentoring.domain.MentoringDay;
import FinTo.domain.mentoring.domain.MentoringDayOfWeek;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MentoringCreateRequestDto {
    private Long mentorId;
    private String title;
    private String content;
    private String notice;
    private List<MentoringDayDto> days;

    @Getter
    @Setter
    public static class MentoringDayDto {
        private String day;
        private List<Integer> times;

        public MentoringDay toEntity(Mentoring mentoring) {
            return MentoringDay.builder()
                    .mentoring(mentoring)
                    .mentoringDay(MentoringDayOfWeek.valueOf(day))
                    .build();
        }
    }

    public Mentoring toEntity(Mentor mentor) {
        return Mentoring.builder()
                .mentor(mentor)
                .title(this.title)
                .content(this.content)
                .notice(this.notice)
                .build();
    }
}

package FinTo.domain.mentoring.dto.response;

import FinTo.domain.mentoring.domain.MentoringDay;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MentoringDayResponseDto {
    private String day;

    public static MentoringDayResponseDto from(MentoringDay entity) {
        return new MentoringDayResponseDto(entity.getMentoringDay().name());
    }
}
package FinTo.domain.mentoring.dto.response;

import FinTo.domain.mentoring.domain.MentoringTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MentoringTimeResponseDto {
    private Long timeId;
    private String startTime;
    private String endTime;

    public static MentoringTimeResponseDto from(MentoringTime entity) {
        int start = entity.getStartTime();
        int end = (start + 1) % 24;

        return new MentoringTimeResponseDto(
                entity.getId(),
                String.format("%02d:00", start),
                String.format("%02d:00", end)
        );
    }
}
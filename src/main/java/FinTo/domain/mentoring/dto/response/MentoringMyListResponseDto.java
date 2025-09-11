package FinTo.domain.mentoring.dto.response;

import FinTo.domain.mentoring.domain.Mentoring;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MentoringMyListResponseDto {
    private Long id;
    private String title;
    private String notice;

    public static MentoringMyListResponseDto fromEntity(Mentoring mentoring) {
        return MentoringMyListResponseDto.builder()
                .id(mentoring.getId())
                .title(mentoring.getTitle())
                .notice(mentoring.getNotice())
                .build();
    }
}

package FinTo.domain.mentoring.dto.response;

import FinTo.domain.mentoring.domain.Mentoring;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MentoringCardResponseDto {
    private Long id;
    private String title;
    private String notice;
    private String mentorName;
    private String mentorProfile;
    private List<String> languages;

    public static MentoringCardResponseDto fromEntity(Mentoring mentoring) {
        return MentoringCardResponseDto.builder()
                .id(mentoring.getId())
                .title(mentoring.getTitle())
                .notice(mentoring.getNotice())
                .mentorName(mentoring.getMentor().getMember().getName())
                .mentorProfile(mentoring.getMentor().getMember().getProfileImg())
                .languages(
                        mentoring.getMentor().getMember().getMemberLanguages().stream()
                                .map(ml -> ml.getLanguage().getName())
                                .toList()
                )
                .build();
    }

}


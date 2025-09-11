package FinTo.domain.mentoring.dto.response;

import FinTo.domain.mentoring.domain.Mentoring;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MentoringResponseDto {
    private Long id;
    private String title;
    private String notice;
    private String mentorName;
    private String mentorProfile;
    private List<String> languages;
    private String nationalityName;
    private String nationalityEmoji;

    public static MentoringResponseDto fromEntity(Mentoring mentoring) {
        return MentoringResponseDto.builder()
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
                .nationalityName(
                        mentoring.getMentor().getMember().getNationality() != null
                                ? mentoring.getMentor().getMember().getNationality().getName()
                                : null
                )
                .nationalityEmoji(
                        mentoring.getMentor().getMember().getNationality() != null
                                ? mentoring.getMentor().getMember().getNationality().getEmoji()
                                : null
                )
                .build();
    }

}


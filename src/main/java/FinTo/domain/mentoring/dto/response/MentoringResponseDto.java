package FinTo.domain.mentoring.dto.response;

import FinTo.domain.mentoring.domain.Mentoring;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MentoringResponseDto {
    private Long mentoringId;
    private String title;
    private String notice;
    private String name;
    private String profileImg;
    private List<String> languages;
    private Double rating;
    private Integer mentees;
    private String nationalityName;
    private String emoji;

    public static MentoringResponseDto fromEntity(Mentoring mentoring) {
        return MentoringResponseDto.builder()
                .mentoringId(mentoring.getId())
                .title(mentoring.getTitle())
                .notice(mentoring.getNotice())
                .name(mentoring.getMentor().getMember().getName())
                .profileImg(mentoring.getMentor().getMember().getProfileImg())
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
                .emoji(
                        mentoring.getMentor().getMember().getNationality() != null
                                ? mentoring.getMentor().getMember().getNationality().getEmoji()
                                : null
                )
                .build();
    }

}


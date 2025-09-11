package FinTo.domain.mentoring.dto.response;

import FinTo.domain.member.domain.Member;
import FinTo.domain.mentor.domain.Mentor;
import FinTo.domain.mentoring.domain.Mentoring;
import FinTo.domain.nationality.domain.Nationality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MentoringDetailResponseDto {
    private Long mentoringId;
    private String title;
    private String content;
    private String profileImg;
    private String name;
    private String emoji;
    private String nation;
    private List<String> languages;
    private Integer mentees;

    public static MentoringDetailResponseDto of(Mentoring mentoring, Mentor mentor, Member member,
                                                Nationality nationality, List<String> languages, Integer menteeCount) {
        return MentoringDetailResponseDto.builder()
                .mentoringId(mentoring.getId())
                .title(mentoring.getTitle())
                .content(mentoring.getContent())
                .profileImg(member.getProfileImg())
                .name(member.getName())
                .emoji(member.getNationality().getEmoji())
                .nation(nationality != null ? nationality.getName() : null)
                .languages(languages)
                .mentees(menteeCount)
                .build();
    }
}

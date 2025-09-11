package FinTo.domain.mentor.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MentorUpgradeRequestDto {
    private Long memberId;
    private String introduce;
}

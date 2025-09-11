package FinTo.domain.mentoring.dto.response;

import FinTo.domain.mentoring.domain.MeetingStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MentoringMeetingResponseDto {
    private Long meetingId;
    private String title;
    private MeetingStatus status;
    private String name;
    private String email;
}

package FinTo.domain.mentoring.dto.response;

import FinTo.domain.mentoring.domain.MeetingStatus;
import FinTo.domain.mentoring.domain.MentoringMeeting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MentoringMeetingDetailResponse {
    private Long meetingId;
    private String name;
    private String email;
    private MeetingStatus status;
    private LocalDate date;
    private String stratTime;
    private String endTime;

    public static MentoringMeetingDetailResponse from(MentoringMeeting mentoringMeeting){
        return MentoringMeetingDetailResponse.builder()
                .meetingId(mentoringMeeting.getId())
                .name(mentoringMeeting.getMember().getName())
                .email(mentoringMeeting.getMember().getEmail())
                .status(mentoringMeeting.getStatus())
                .date(mentoringMeeting.getReservationTime().toLocalDate())
                .stratTime(mentoringMeeting.getReservationTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .endTime(mentoringMeeting.getReservationTime().plusHours(1).format(DateTimeFormatter.ofPattern("HH:mm")))
                .build();
    }
}

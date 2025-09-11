package FinTo.domain.calendar.service;

import FinTo.domain.mentoring.domain.MentoringMeeting;

import java.time.LocalDateTime;

public interface CalendarService {
    void saveCalendar(MentoringMeeting meeting, LocalDateTime bookDatetime);
}

package FinTo.domain.calendar.service;

import FinTo.domain.calendar.domain.Calendar;
import FinTo.domain.calendar.repository.CalendarRepository;
import FinTo.domain.mentoring.domain.MentoringMeeting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    @Override
    public void saveCalendar(MentoringMeeting meeting, LocalDateTime bookDatetime) {
        Calendar calendar = Calendar.builder()
                .meeting(meeting)
                .bookDatetime(bookDatetime)
                .build();
        calendarRepository.save(calendar);
    }
}


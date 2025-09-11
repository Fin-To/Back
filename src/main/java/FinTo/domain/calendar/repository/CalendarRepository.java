package FinTo.domain.calendar.repository;

import FinTo.domain.calendar.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}

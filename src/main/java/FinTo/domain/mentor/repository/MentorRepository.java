package FinTo.domain.mentor.repository;

import FinTo.domain.mentor.domain.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
}

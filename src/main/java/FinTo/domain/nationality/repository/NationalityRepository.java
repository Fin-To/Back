package FinTo.domain.nationality.repository;

import FinTo.domain.nationality.domain.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalityRepository extends JpaRepository<Nationality, Long> {
}

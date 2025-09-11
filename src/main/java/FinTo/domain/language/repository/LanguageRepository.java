package FinTo.domain.language.repository;

import FinTo.domain.language.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findAllByOrderByNameAsc();
}

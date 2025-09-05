package FinTo.domain.language.repository;

import FinTo.domain.language.domain.MemberLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLanguageRepository extends JpaRepository<MemberLanguage, Long> {
    void deleteAllByMemberId(Long memberId);
}

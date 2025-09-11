package FinTo.domain.language.repository;

import FinTo.domain.language.domain.Language;
import FinTo.domain.language.domain.MemberLanguage;
import FinTo.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberLanguageRepository extends JpaRepository<MemberLanguage, Long> {
    void deleteAllByMemberId(Long memberId);

    boolean existsByMemberAndLanguage(Member member, Language language);

    Optional<MemberLanguage> findByMemberAndLanguage(Member member, Language language);
}

package FinTo.domain.language.repository;

import FinTo.domain.language.domain.Language;
import FinTo.domain.language.domain.MemberLanguage;
import FinTo.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberLanguageRepository extends JpaRepository<MemberLanguage, Long> {
    void deleteAllByMemberId(Long memberId);

    boolean existsByMemberAndLanguage(Member member, Language language);

    Optional<MemberLanguage> findByMemberAndLanguage(Member member, Language language);

    @Query("""
        SELECT l.name
        FROM MemberLanguage ml
        JOIN ml.language l
        WHERE ml.member.id = :memberId
        """)
    List<String> findLanguageNamesByMemberId(@Param("memberId") Long memberId);
}

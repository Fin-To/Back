package FinTo.domain.mentoring.repository;

import FinTo.domain.mentoring.domain.Mentoring;
import FinTo.domain.mentoring.dto.request.MentoringSearchCondition;
import FinTo.domain.mentoring.dto.request.MentoringSortType;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static FinTo.domain.language.domain.QLanguage.language;
import static FinTo.domain.language.domain.QMemberLanguage.memberLanguage;
import static FinTo.domain.member.domain.QMember.member;
import static FinTo.domain.mentoring.domain.QMentoring.mentoring;
import static FinTo.domain.review.domain.QReview.review;

@Repository
@RequiredArgsConstructor
public class MentoringQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<Mentoring> search(MentoringSearchCondition condition, Pageable pageable) {
        // 멘토링 목록 조회
        List<Mentoring> content = queryFactory
                .selectDistinct(mentoring)
                .from(mentoring)
                .where(titleContains(condition.getTitle()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 개수 조회
        Long count = queryFactory
                .select(mentoring.countDistinct())
                .from(mentoring)
                .where(titleContains(condition.getTitle()))
                .fetchOne();

        return new PageImpl<>(content, pageable, count != null ? count : 0);
    }

    // === 조건절 ===
    private BooleanExpression titleContains(String title) {
        return (title != null && !title.isEmpty()) ? mentoring.title.containsIgnoreCase(title) : null;
    }
}

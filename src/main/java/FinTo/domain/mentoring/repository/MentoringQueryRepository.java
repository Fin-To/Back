package FinTo.domain.mentoring.repository;

import FinTo.domain.mentoring.domain.Mentoring;
import FinTo.domain.mentoring.domain.QMentoring;
import FinTo.domain.mentoring.dto.request.MentoringSearchCondition;
import FinTo.domain.mentoring.dto.request.MentoringSortType;
import FinTo.domain.mentoring.dto.response.MentoringResponseDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static FinTo.domain.language.domain.QLanguage.language;
import static FinTo.domain.language.domain.QMemberLanguage.memberLanguage;
import static FinTo.domain.member.domain.QMember.member;
import static FinTo.domain.mentor.domain.QMentor.mentor;
import static FinTo.domain.mentoring.domain.QMentoring.mentoring;
import static FinTo.domain.nationality.domain.QNationality.nationality;
import static FinTo.domain.review.domain.QReview.review;

@Repository
@RequiredArgsConstructor
public class MentoringQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<MentoringResponseDto> search(MentoringSearchCondition condition, Pageable pageable) {
        // 멘토링 목록 조회
        List<Tuple> tuples = queryFactory
                .select(
                        mentoring.id,
                        mentoring.title,
                        mentoring.notice,
                        member.name,
                        member.profileImg,
                        nationality.name,
                        nationality.emoji,
                        review.rating.avg(),
                        review.count()
                )
                .from(mentoring)
                .leftJoin(mentoring.mentor, mentor)
                .leftJoin(mentor.member, member)
                .leftJoin(member.nationality, nationality)
                .leftJoin(review).on(review.mentoring.eq(mentoring))
                .where(titleContains(condition.getTitle()))
                .groupBy(mentoring.id, member.id, mentor.id, nationality.id)
                .orderBy(getOrderSpecifier(condition.getSortType()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2️⃣ 언어 정보 조회
        List<Long> mentoringIds = tuples.stream().map(t -> t.get(mentoring.id)).toList();

        Map<Long, List<String>> mentoringLanguages = queryFactory
                .select(memberLanguage.member.id, language.name)
                .from(memberLanguage)
                .join(memberLanguage.language, language)
                .where(memberLanguage.member.id.in(mentoringIds))
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(
                        tuple -> tuple.get(memberLanguage.member.id),
                        Collectors.mapping(tuple -> tuple.get(language.name), Collectors.toList())
                ));

        // 3️⃣ DTO 변환
        List<MentoringResponseDto> content = tuples.stream().map(t -> MentoringResponseDto.builder()
                        .id(t.get(mentoring.id))
                        .title(t.get(mentoring.title))
                        .notice(t.get(mentoring.notice))
                        .name(t.get(member.name))
                        .profileImg(t.get(member.profileImg))
                        .languages(mentoringLanguages.getOrDefault(t.get(member.id), List.of()))
                        .rating(t.get(review.rating.avg()) != null ? t.get(review.rating.avg()) : 0.0)
                        .mentees(t.get(review.count()) != null ? t.get(review.count()).intValue() : 0)
                        .nationalityName(t.get(nationality.name))
                        .emoji(t.get(nationality.emoji))
                        .build())
                .toList();

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

    private OrderSpecifier<?> getOrderSpecifier(MentoringSortType sortType) {
        return switch (sortType) {
            case TITLE -> mentoring.title.asc();
            case RATING -> review.rating.avg().desc();
        };
    }
}

//        List<MentoringResponseDto> tuples = queryFactory
//                .select(Projections.constructor(
//                        MentoringResponseDto.class,
//                        mentoring.id,
//                        mentoring.title,
//                        mentoring.notice,
//                        mentoring.content,
//                        member.name,
//                        review.rating.avg(),
//                        review.count()
//                ))
//                .from(mentoring)
//                .leftJoin(mentoring.mentor, mentor)
//                .leftJoin(mentor.member, member)
//                .leftJoin(review).on(review.mentoring.eq(mentoring))
//                .where(titleContains(condition.getTitle()))
//                .groupBy(mentoring.id)
//                .orderBy(getOrderSpecifier(condition.getSortType()))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();

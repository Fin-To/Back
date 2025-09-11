package FinTo.domain.mentoring.dto.request;

import lombok.Data;

@Data
public class MentoringSearchCondition {
    private Long nationalityId;
    private Long languageId;
    private String title;
    private MentoringSortType sortType = MentoringSortType.RATING;
}


package FinTo.domain.nationality.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NationalityResponseDto {
    private Long id;
    private String name;
    private String code;
    private String emoji;
}

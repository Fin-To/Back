package FinTo.domain.language.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LanguagesResponseDto {
    private List<LanguageResponseDto> languages;
}

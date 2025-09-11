package FinTo.domain.language.service;

import FinTo.domain.language.dto.LanguageCreateRequestDto;
import FinTo.domain.language.dto.LanguagesResponseDto;

public interface LanguageService {
    void create(LanguageCreateRequestDto requestDto);

    LanguagesResponseDto getAll();
}

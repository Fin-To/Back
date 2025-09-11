package FinTo.domain.language.service;

import FinTo.domain.language.dto.LanguageCreateRequestDto;
import FinTo.domain.language.dto.LanguageUpdateRequestDto;
import FinTo.domain.language.dto.LanguagesResponseDto;

public interface LanguageService {
    void create(LanguageCreateRequestDto requestDto);

    LanguagesResponseDto getAll();

    void update(Long languageId, LanguageUpdateRequestDto requestDto);

    void delete(Long languageId);

    void addToMyLanguages(Long memberId, Long languageId);

    void deleteFromMyLanguages(Long memberId, Long languageId);
}

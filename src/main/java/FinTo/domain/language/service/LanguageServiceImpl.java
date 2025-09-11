package FinTo.domain.language.service;

import FinTo.domain.language.domain.Language;
import FinTo.domain.language.dto.LanguageCreateRequestDto;
import FinTo.domain.language.dto.LanguageResponseDto;
import FinTo.domain.language.dto.LanguageUpdateRequestDto;
import FinTo.domain.language.dto.LanguagesResponseDto;
import FinTo.domain.language.exception.LanguageNotFoundException;
import FinTo.domain.language.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    private Language getById(Long languageId) {
        return languageRepository.findById(languageId).orElseThrow(LanguageNotFoundException::new);
    }

    @Override
    public void create(LanguageCreateRequestDto requestDto) {
        Language language = new Language();
        language.setName(requestDto.getName());
        languageRepository.save(language);
    }

    @Override
    public LanguagesResponseDto getAll() {
        List<LanguageResponseDto> languageDtos = languageRepository.findAllByOrderByNameAsc()
                .stream()
                .map(l -> new LanguageResponseDto(l.getId(), l.getName()))
                .toList();

        return new LanguagesResponseDto(languageDtos);
    }

    @Override
    public void update(Long languageId, LanguageUpdateRequestDto requestDto) {
        Language language = getById(languageId);
        language.setName(requestDto.getName());
    }

    @Override
    public void delete(Long languageId) {
        languageRepository.deleteById(languageId);
    }
}

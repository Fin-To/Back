package FinTo.domain.language.service;

import FinTo.domain.language.domain.Language;
import FinTo.domain.language.domain.MemberLanguage;
import FinTo.domain.language.dto.LanguageCreateRequestDto;
import FinTo.domain.language.dto.LanguageResponseDto;
import FinTo.domain.language.dto.LanguageUpdateRequestDto;
import FinTo.domain.language.dto.LanguagesResponseDto;
import FinTo.domain.language.exception.AlreadyHasLanguageException;
import FinTo.domain.language.exception.LanguageNotFoundException;
import FinTo.domain.language.repository.LanguageRepository;
import FinTo.domain.language.repository.MemberLanguageRepository;
import FinTo.domain.member.domain.Member;
import FinTo.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final MemberLanguageRepository memberLanguageRepository;
    private final MemberService memberService;

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

    @Override
    public void addToMyLanguages(Long memberId, Long languageId) {
        Member member = memberService.getById(memberId);
        Language language = getById(languageId);

        if (memberLanguageRepository.existsByMemberAndLanguage(member, language)) {
            throw new AlreadyHasLanguageException();
        }

        MemberLanguage memberLanguage = new MemberLanguage();
        memberLanguage.setMember(member);
        memberLanguage.setLanguage(language);
        memberLanguageRepository.save(memberLanguage);
    }

    @Override
    public LanguagesResponseDto getMyLanguages(Long memberId) {
        Member member = memberService.getById(memberId);
        List<LanguageResponseDto> languageResponseDtos = memberLanguageRepository.findAllByMember(member)
                .stream()
                .map(ml -> new LanguageResponseDto(
                        ml.getLanguage().getId(),
                        ml.getLanguage().getName()
                ))
                .toList();
        return new LanguagesResponseDto(languageResponseDtos);
    }

    @Override
    public void deleteFromMyLanguages(Long memberId, Long languageId) {
        Member member = memberService.getById(memberId);
        Language language = getById(languageId);

        MemberLanguage memberLanguage = memberLanguageRepository.findByMemberAndLanguage(member, language)
                .orElseThrow(() -> new IllegalArgumentException("추가되지 않은 언어"));
        memberLanguageRepository.delete(memberLanguage);
    }
}

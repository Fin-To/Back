package FinTo.domain.language.service;

import FinTo.domain.language.repository.MemberLanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberLanguageServiceImpl implements MemberLanguageService {

    private final MemberLanguageRepository memberLanguageRepository;
    private final LanguageService languageService;
}

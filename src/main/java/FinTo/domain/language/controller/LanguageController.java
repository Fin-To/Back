package FinTo.domain.language.controller;

import FinTo.domain.language.dto.LanguageCreateRequestDto;
import FinTo.domain.language.dto.LanguagesResponseDto;
import FinTo.domain.language.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody LanguageCreateRequestDto requestDto) {
        languageService.create(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<LanguagesResponseDto> getAll() {
        return ResponseEntity.ok(languageService.getAll());
    }
}

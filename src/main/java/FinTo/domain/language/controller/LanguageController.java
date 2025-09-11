package FinTo.domain.language.controller;

import FinTo.domain.language.dto.LanguageCreateRequestDto;
import FinTo.domain.language.dto.LanguagesResponseDto;
import FinTo.domain.language.dto.LanguageUpdateRequestDto;
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

    @PutMapping("/{languageId}")
    public ResponseEntity<Void> update(
        @PathVariable Long languageId,
        @Validated @RequestBody LanguageUpdateRequestDto requestDto
    ) {
        languageService.update(languageId, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long languageId
    ) {
        languageService.delete(languageId);
        return ResponseEntity.ok().build();
    }
}

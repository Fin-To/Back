package FinTo.domain.language.controller;

import FinTo.domain.language.dto.LanguageCreateRequestDto;
import FinTo.domain.language.dto.LanguagesResponseDto;
import FinTo.domain.language.dto.LanguageUpdateRequestDto;
import FinTo.domain.language.service.LanguageService;
import FinTo.global.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        @PathVariable(name = "languageId") Long languageId,
        @Validated @RequestBody LanguageUpdateRequestDto requestDto
    ) {
        languageService.update(languageId, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "languageId") Long languageId
    ) {
        languageService.delete(languageId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/me/{languageId}")
    public ResponseEntity<Void> addToMyLanguages(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable(name = "languageId") Long languageId
    ) {
        languageService.addToMyLanguages(userDetails.getId(), languageId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me/{languageId}")
    public ResponseEntity<Void> removeFromMyLanguages(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable(name = "languageId") Long languageId
    ) {
        languageService.deleteFromMyLanguages(userDetails.getId(), languageId);
        return ResponseEntity.ok().build();
    }
}

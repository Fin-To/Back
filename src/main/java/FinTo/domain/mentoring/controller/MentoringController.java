package FinTo.domain.mentoring.controller;

import FinTo.domain.mentoring.dto.request.MentoringCreateRequestDto;
import FinTo.domain.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentoring")
@RequiredArgsConstructor
public class MentoringController {

    private final MentoringService mentoringService;

    @PostMapping
    public ResponseEntity<Void> createMentoring(@RequestBody MentoringCreateRequestDto requestDto) {
        mentoringService.createMentoring(requestDto);
        return ResponseEntity.ok().build();
    }
}

package FinTo.domain.mentoring.controller;

import FinTo.domain.mentoring.dto.request.MentoringCreateRequestDto;
import FinTo.domain.mentoring.dto.request.MentoringUpdateRequestDto;
import FinTo.domain.mentoring.dto.response.MentoringCardResponseDto;
import FinTo.domain.mentoring.dto.response.MentoringDayResponseDto;
import FinTo.domain.mentoring.dto.response.MentoringMyListResponseDto;
import FinTo.domain.mentoring.dto.response.MentoringTimeResponseDto;
import FinTo.domain.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentorings")
@RequiredArgsConstructor
public class MentoringController {

    private final MentoringService mentoringService;

    @PostMapping
    public ResponseEntity<Void> createMentoring(@RequestBody MentoringCreateRequestDto requestDto) {
        mentoringService.createMentoring(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Page<MentoringMyListResponseDto>> getMyMentorings(
            @RequestParam Long mentorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(mentoringService.getMyMentorings(mentorId, pageable));
    }

    @GetMapping
    public ResponseEntity<Page<MentoringCardResponseDto>> getAllMentorings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(mentoringService.getAllMentorings(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateMentoring(
            @PathVariable Long id,
            @RequestBody MentoringUpdateRequestDto requestDto
    ) {
        mentoringService.updateMentoring(id, requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{mentoringId}/days")
    public ResponseEntity<List<MentoringDayResponseDto>> getDays(@PathVariable Long mentoringId) {
        return ResponseEntity.ok(mentoringService.getMentoringDays(mentoringId));
    }

    @GetMapping("/{mentoringId}/times")
    public ResponseEntity<List<MentoringTimeResponseDto>> getTimes(
            @PathVariable Long mentoringId,
            @RequestParam String day
    ) {
        return ResponseEntity.ok(mentoringService.getMentoringTimes(mentoringId, day));
    }
}

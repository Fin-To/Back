package FinTo.domain.mentoring.controller;

import FinTo.domain.mentoring.dto.request.MentoringCreateRequestDto;
import FinTo.domain.mentoring.dto.request.MentoringSearchCondition;
import FinTo.domain.mentoring.dto.request.MentoringUpdateRequestDto;
import FinTo.domain.mentoring.dto.response.*;
import FinTo.domain.mentoring.service.MentoringMeetingService;
import FinTo.domain.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentoring")
@RequiredArgsConstructor
public class MentoringController {

    private final MentoringService mentoringService;
    private final MentoringMeetingService mentoringMeetingService;

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

    @GetMapping("/me/requests")
    public ResponseEntity<Page<MentoringMeetingResponseDto>> getMentoringMeetings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(mentoringMeetingService.getMentoringMeetings(pageable));
    }

    @GetMapping
    public ResponseEntity<Page<MentoringResponseDto>> search(
            @RequestParam(name = "title", required = false) String title,
            Pageable pageable
            ) {
        MentoringSearchCondition condition = new MentoringSearchCondition();
        condition.setTitle(title);
        return ResponseEntity.ok(mentoringService.search(condition, pageable));
    }

    @GetMapping("/{mentoringId}")
    public ResponseEntity<MentoringDetailResponseDto> getMentoring(
            @PathVariable long mentoringId
    ){
        return ResponseEntity.ok(mentoringService.getMentoring(mentoringId));
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

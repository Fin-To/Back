package FinTo.domain.mentor.controller;

import FinTo.domain.mentor.dto.request.MentorUpgradeRequestDto;
import FinTo.domain.mentor.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentor")
@RequiredArgsConstructor
public class MentorController {

    private final MentorService mentorService;

    @PostMapping("/upgrade")
    public ResponseEntity<Void> upgradeToMentor(@RequestBody MentorUpgradeRequestDto requestDto) {
        mentorService.upgradeToMentor(requestDto);
        return ResponseEntity.ok().build();
    }
}

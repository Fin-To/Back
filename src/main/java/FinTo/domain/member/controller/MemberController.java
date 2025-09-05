package FinTo.domain.member.controller;

import FinTo.domain.member.dto.MemberCreateRequestDto;
import FinTo.domain.member.dto.MemberResponseDto;
import FinTo.domain.member.dto.MemberUpdateRequestDto;
import FinTo.domain.member.service.MemberService;
import FinTo.global.security.userdetails.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> me(@AuthenticationPrincipal CustomUserDetails userDetails){
        return MemberResponseDto.toResponseEntity(memberService.getById(userDetails.getId()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MemberResponseDto> member(@PathVariable(value = "userId") Long userId){
        return MemberResponseDto.toResponseEntity(memberService.getById(userId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MemberCreateRequestDto requestDto) {
        memberService.create(requestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateMe(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody MemberUpdateRequestDto requestDto
    ) {
        memberService.update(userDetails.getId(), requestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Void> update(
            @PathVariable(value = "userId") Long userId,
            @RequestBody MemberUpdateRequestDto requestDto
            ) {
        memberService.update(userId, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> updateMe(@AuthenticationPrincipal CustomUserDetails userDetails) {
        memberService.delete(userDetails.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> updateMe(@PathVariable(value = "userId") Long userId) {
        memberService.delete(userId);
        return ResponseEntity.ok().build();
    }
}

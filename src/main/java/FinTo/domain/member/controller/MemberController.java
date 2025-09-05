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

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> member(@PathVariable(value = "memberId") Long memberId){
        return MemberResponseDto.toResponseEntity(memberService.getById(memberId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MemberCreateRequestDto requestDto) {
        memberService.create(requestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me")
    public ResponseEntity<Void> updateMe(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody MemberUpdateRequestDto requestDto
    ) {
        memberService.update(userDetails.getId(), requestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> update(
            @PathVariable(value = "memberId") Long memberId,
            @RequestBody MemberUpdateRequestDto requestDto
            ) {
        memberService.update(memberId, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> updateMe(@AuthenticationPrincipal CustomUserDetails userDetails) {
        memberService.delete(userDetails.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> updateMe(@PathVariable(value = "memberId") Long memberId) {
        memberService.delete(memberId);
        return ResponseEntity.ok().build();
    }
}

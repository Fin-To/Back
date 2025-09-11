package FinTo.global.mail;

import FinTo.domain.member.domain.Member;
import FinTo.domain.member.domain.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    // 임시 테스트용 API입니다.
    @PostMapping("/send")
    public ResponseEntity<Void> testSendMail() {
        Member mentor = Member.of("멘토", OAuthProvider.GOOGLE,"멘토아이디","simchung2go@gmail.com");
        Member mentee = Member.of("멘티", OAuthProvider.GOOGLE, "멘티아이디", "simcheong2go@gmail.com");
        mailService.sendMail(mentor,mentee,"멘토링 신청");
        return ResponseEntity.ok().build();
    }

}

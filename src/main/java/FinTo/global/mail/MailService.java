package FinTo.global.mail;

import FinTo.domain.member.domain.Member;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.profiles.active:prod}")
    private String activeProfile;

    public void sendMail(Member mentor, Member mentee, String message){
        if ("dev".equals(activeProfile)) {
            // 개발환경에서는 콘솔에만 출력
            log.info("=== [DEV] 이메일 발송 시뮬레이션 ===");
            log.info("수신자: {}", mentor.getEmail());
            log.info("제목: 새로운 멘토링이 성사되었습니다.");
            log.info("내용: {}님의 멘토링 신청 - {}, 날짜 : ~~~", mentee.getName(), message);
            log.info("=====================================");

            log.info("=== [DEV] 이메일 발송 시뮬레이션 ===");
            log.info("수신자: {}", mentee.getEmail());
            log.info("제목: 새로운 멘토링이 성사되었습니다.");
            log.info("내용: {}님의 멘토링 신청 완료 - {}, 날짜 : ~~~", mentor.getName(), message);
            log.info("=====================================");
            return;
        }
        try{
            // 멘티에게 매칭 완료 이메일
            sendEmailToMentee(mentee, mentor, message);

            // 멘토에게 매칭 완료 이메일
            sendEmailToMentor(mentee, mentor, message);

        }
        catch (Exception e){
            log.error("멘토링 신청 알림 이메일 발송 실패: {}", mentor.getEmail(), e);
            throw new RuntimeException("이메일 발송에 실패했습니다.");
        }
    }

    private void sendEmailToMentee(Member mentee, Member mentor, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(mentee.getEmail());
        helper.setSubject("멘티님의 멘토링 신청이 성사되었습니다" + LocalDateTime.now());

        String content = LocalDateTime.now().toString()+ "시에 " + mentor.getName() + " 님의 \"내가 알려줄게\" 멘토링 수강 신청이 승인되었습니다. " + message;
        helper.setText(content, true);

        mailSender.send(mimeMessage);
        log.info("멘티에게 메일 송신 완료");
    }

    private void sendEmailToMentor(Member mentee, Member mentor, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(mentor.getEmail());
        helper.setSubject("멘토링 날짜가 확정되었습니다" + LocalDateTime.now());

        String content = LocalDateTime.now().toString()+ "시에 " + mentee.getName() + " 님과의 멘토링이 확정되었습니다. " + message;
        helper.setText(content, true);

        mailSender.send(mimeMessage);
        log.info("멘토에게 메일 송신 완료");
    }
}

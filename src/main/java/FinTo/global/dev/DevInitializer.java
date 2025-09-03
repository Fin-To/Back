package FinTo.global.dev;

import FinTo.domain.member.domain.Member;
import FinTo.domain.member.repository.MemberRepository;
import FinTo.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * dev 프로필에서만 테스트용 데이터를 자동으로 넣기 위한 컴포넌트입니다.
 * 필요한 경우 application.properties 에서 spring.profiles.active=dev 를 추가하여
 * 사용할 수 있습니다.
 */
@Component
@Profile("dev")
@RequiredArgsConstructor
@Slf4j
public class DevInitializer implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void run(String... args) throws Exception {
        Member dev = memberRepository.findByName("dev")
                .orElseGet(() -> {
                    Member member = Member.of("dev");
                    memberRepository.save(member);
                    log.info("개발용 Member 생성: id={}, name={}",
                            member.getId(), member.getName());
                    return member;
                });

        String accessToken = jwtTokenProvider.createAccessToken(dev.getId());

        log.info("""
                
                ==================== [Dev Access Token] ====================
                {}
                ============================================================
                """,
                accessToken
        );
    }
}

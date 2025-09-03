package FinTo.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // NOT FOUND
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 유저가 없습니다."),

    // UNAUTHORIZED
    AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "인증이 필요한 요청입니다."),
    JWT_AUTHENTICATION_FAIL(HttpStatus.UNAUTHORIZED, "JWT 인증에 실패하였습니다."),

    // ETC
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final HttpStatus status;
    private final String message;
}

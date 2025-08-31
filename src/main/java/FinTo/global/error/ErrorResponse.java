package FinTo.global.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private final String code;
    private final String message;
    private final String path;
    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();

    public static ResponseEntity<ErrorResponse> create(ErrorCode code, String path) {
        return ResponseEntity.status(code.getStatus()).body(
                ErrorResponse.builder()
                        .code(code.toString())
                        .message(code.getMessage())
                        .path(path)
                        .build()
        );
    }
}

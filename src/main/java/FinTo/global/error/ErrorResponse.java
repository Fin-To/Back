package FinTo.global.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private final String status;
    private final String error;
    private final String path;
    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();

    public static ResponseEntity<ErrorResponse> create(ErrorCode code, String path) {
        return ResponseEntity.status(code.getStatus()).body(
                ErrorResponse.builder()
                        .status(code.toString())
                        .error(code.getMessage())
                        .path(path)
                        .build()
        );
    }
}

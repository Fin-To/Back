package FinTo.global.error;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e, HttpServletRequest request) {
        log.info("BaseException 발생: 요청 [{}], 코드 [{}], 메시지 [{}]",
                request.getRequestURI(), e.getErrorCode(), e.getMessage());
        return ErrorResponse.create(e.getErrorCode(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        log.error("예기치 못한 예외 발생: 요청 [{}]", request.getRequestURI(), e);
        return ErrorResponse.create(ErrorCode.INTERNAL_SERVER_ERROR, request.getRequestURI());
    }
}

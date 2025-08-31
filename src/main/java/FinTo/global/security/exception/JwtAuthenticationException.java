package FinTo.global.security.exception;

import FinTo.global.error.BaseException;
import FinTo.global.error.ErrorCode;

public class JwtAuthenticationException extends BaseException {
    public JwtAuthenticationException() {
        super(ErrorCode.JWT_AUTHENTICATION_FAIL);
    }
}

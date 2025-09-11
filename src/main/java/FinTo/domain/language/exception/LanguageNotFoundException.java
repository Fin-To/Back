package FinTo.domain.language.exception;

import FinTo.global.error.BaseException;
import FinTo.global.error.ErrorCode;

public class LanguageNotFoundException extends BaseException {
    public LanguageNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}

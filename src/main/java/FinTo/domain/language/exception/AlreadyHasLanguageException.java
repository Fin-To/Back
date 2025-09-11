package FinTo.domain.language.exception;

import FinTo.global.error.BaseException;
import FinTo.global.error.ErrorCode;

public class AlreadyHasLanguageException extends BaseException {
    public AlreadyHasLanguageException() {
        super(ErrorCode.BAD_REQUEST);
    }
}

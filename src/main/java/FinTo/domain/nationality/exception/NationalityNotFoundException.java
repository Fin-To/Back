package FinTo.domain.nationality.exception;

import FinTo.global.error.BaseException;
import FinTo.global.error.ErrorCode;

public class NationalityNotFoundException extends BaseException {
    public NationalityNotFoundException() {
        super(ErrorCode.NATIONALITY_NOT_FOUND);
    }
}

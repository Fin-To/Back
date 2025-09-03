package FinTo.domain.member.exception;

import FinTo.global.error.BaseException;
import FinTo.global.error.ErrorCode;

public class MemberNotFoundException extends BaseException {
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}

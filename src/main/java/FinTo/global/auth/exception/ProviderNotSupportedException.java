package FinTo.global.auth.exception;

import FinTo.global.error.BaseException;
import FinTo.global.error.ErrorCode;

public class ProviderNotSupportedException extends BaseException {
    public ProviderNotSupportedException() {
        super(ErrorCode.PROVIDER_NOT_SUPPORTED);
    }
}

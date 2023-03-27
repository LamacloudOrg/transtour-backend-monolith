package com.transtour.kernel.domain.exception;

public class NotFound extends DomainException {
    public NotFound(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}

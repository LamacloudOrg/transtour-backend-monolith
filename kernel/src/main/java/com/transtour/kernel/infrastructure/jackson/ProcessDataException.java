package com.transtour.kernel.infrastructure.jackson;

public class ProcessDataException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public ProcessDataException(String errorCode, String errorMessage) {
        super(errorMessage);

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ProcessDataException(Throwable cause) {
        super(cause);

        this.errorCode = cause.getMessage();
        this.errorMessage = cause.getMessage();
    }

    public String errorCode() {
        return errorCode;
    }

    public String errorMessage() {
        return errorMessage;
    }

}

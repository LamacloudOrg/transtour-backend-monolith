package com.transtour.kernel.domain;

public class ResponseError {

    private final String timestamp;
    private final String status;
    private final String error;
    private final String exception;
    private final String message;

    public ResponseError(String timestamp, String status, String error, String exception, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.exception = exception;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}

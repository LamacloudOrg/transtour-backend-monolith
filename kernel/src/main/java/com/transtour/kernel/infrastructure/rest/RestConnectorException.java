package com.transtour.kernel.infrastructure.rest;

public class RestConnectorException extends RuntimeException {
    private static final long serialVersionUID = -1649386272171431897L;
    private final int status;
    private final String message;

    public RestConnectorException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

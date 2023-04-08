package com.transtour.kernel.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {


    public BadRequestException(String message) {

        super("{ status:" + HttpStatus.BAD_REQUEST.value() + "" +
                ", message:" + message
                + " }");
    }
}

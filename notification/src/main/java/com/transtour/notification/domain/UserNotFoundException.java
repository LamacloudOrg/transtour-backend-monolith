package com.transtour.notification.domain;

import com.transtour.kernel.exceptions.BadRequestException;

public class UserNotFoundException extends BadRequestException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

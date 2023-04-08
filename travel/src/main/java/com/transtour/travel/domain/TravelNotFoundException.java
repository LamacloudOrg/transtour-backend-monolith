package com.transtour.travel.domain;

import com.transtour.kernel.exceptions.BadRequestException;

public class TravelNotFoundException extends BadRequestException {

    public TravelNotFoundException(String message) {
        super(message);
    }
}

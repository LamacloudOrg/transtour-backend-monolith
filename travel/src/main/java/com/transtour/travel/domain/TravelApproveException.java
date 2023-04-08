package com.transtour.travel.domain;

import com.transtour.kernel.exceptions.BadRequestException;

public class TravelApproveException extends BadRequestException {

    public TravelApproveException(String message) {
        super(message);
    }
}

package com.transtour.travel.domain;

public class TravelNotReady extends RuntimeException {

    public TravelNotReady() {
        super("Travel is not ready");
    }
}


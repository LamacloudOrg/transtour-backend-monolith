package com.transtour.user.application;

import com.transtour.kernel.domain.bus.query.Response;

import java.util.List;

public class DriversResponse implements Response {

    private final List<DriverResponse> drivers;

    public DriversResponse(List<DriverResponse> drivers) {
        this.drivers = drivers;
    }
}

package com.transtour.user.application;

import com.transtour.kernel.domain.bus.query.Response;
import lombok.Getter;

import java.util.List;

@Getter
public class DriversResponse implements Response {

    private final List<DriverResponse> drivers;

    public DriversResponse(List<DriverResponse> drivers) {
        this.drivers = drivers;
    }
}

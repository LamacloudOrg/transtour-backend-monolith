package com.transtour.user.application;

import com.transtour.kernel.domain.bus.query.Response;
import com.transtour.user.domain.Car;
import lombok.Getter;

import java.util.Set;

@Getter
public class DriverResponse implements Response {

    private final String name;
    private final Set<Car> cars;
    private final String dni;

    public DriverResponse(String name, Set<Car> cars, String dni) {
        this.name = name;
        this.cars = cars;
        this.dni = dni;
    }
}

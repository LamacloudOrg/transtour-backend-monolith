package com.transtour.user.application;

import com.transtour.kernel.domain.bus.query.Response;

import java.util.List;

public class DriverResponse implements Response {

    private final String name;
    private final List<String> patente;
    private final String dni;

    public DriverResponse(String name, List<String> patente, String dni) {
        this.name = name;
        this.patente = patente;
        this.dni = dni;
    }
}

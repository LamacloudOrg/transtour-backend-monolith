package com.transtour.kernel.exceptions;

public class DriverNotExists extends RuntimeException {

    public DriverNotExists() {
        super("Driver not exists");
    }

}

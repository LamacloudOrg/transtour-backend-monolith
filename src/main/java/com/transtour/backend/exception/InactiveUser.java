package com.transtour.backend.exception;

public class InactiveUser extends RuntimeException {

    public InactiveUser() {
        super("Usuario Inactivo");
    }
}

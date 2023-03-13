package com.transtour.backend.exception;

public class UserNotExists extends RuntimeException {

    public UserNotExists() {
        super("Usuario no existe");
    }
}

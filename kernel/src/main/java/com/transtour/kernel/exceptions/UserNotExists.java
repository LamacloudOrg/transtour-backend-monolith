package com.transtour.kernel.exceptions;

public class UserNotExists extends RuntimeException{

    public UserNotExists() {
        super("Uers not exists");
    }
}

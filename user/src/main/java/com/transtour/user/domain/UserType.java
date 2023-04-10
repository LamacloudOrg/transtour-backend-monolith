package com.transtour.user.domain;

public enum UserType {

    USER("ROL_USER"),
    DRIVER("ROL_DRIVER"),
    ADMIN("ROL_ADMIN");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}

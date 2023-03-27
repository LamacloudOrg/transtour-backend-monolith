package com.transtour.security.oauth.application.authentication.query;

import com.transtour.kernel.domain.bus.query.Query;
import lombok.Getter;

@Getter
public class AunthenticationQuery implements Query {

    private final String dni;
    private final String password;

    public AunthenticationQuery(String dni, String password) {
        this.dni = dni;
        this.password = password;
    }
}

package com.transtour.security.oauth.infrastructure.controllers.authentication;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String dni;
    private String password;
}

package com.transtour.security.oauth.dtos;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String dni;
    private String password;
}

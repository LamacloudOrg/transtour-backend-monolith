package com.transtour.backend.dto;

import lombok.Data;
@Data
public class AuthenticationRequest {
    private String dni;
    private String password;
}

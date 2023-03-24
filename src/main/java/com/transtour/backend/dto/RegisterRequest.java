package com.transtour.backend.dto;
import com.transtour.backend.model.UserStatus;
import lombok.Data;

@Data
public class RegisterRequest {

    private String id;
    private String dni;
    private String password;
    private String fullName;
    private String phone;
    private UserStatus status;

    private String email;
}

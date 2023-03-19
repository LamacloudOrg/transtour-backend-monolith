package com.transtour.backend.dto;
import lombok.Data;

@Data
public class UserDTO {
    private Long dni;
    private String fullName;
    private String phone;
    private String mobilePhone;
    private String email;
}

package com.transtour.security.oauth.dtos;

import com.transtour.kernel.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.RoleStatus;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String id;
    private String dni;
    private String email;

    private String fullName;
    private String password;

    private UserStatus status;

    private String phone;

    private List<RoleStatus> roles;

}

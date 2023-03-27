package com.transtour.security.oauth.application.registration.command;

import com.transtour.kernel.domain.bus.command.Command;
import com.transtour.kernel.domain.user.UserStatus;
import lombok.Getter;

import javax.management.relation.RoleStatus;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
@Getter
public class RegistrationCommand implements Command {


    private final String id;
    private final String dni;
    private final String email;

    private final String fullName;
    private final String password;

    private final UserStatus status;

    private final String phone;

    private final List<RoleStatus> roles;


    public RegistrationCommand(String id, String dni, String email, String fullName, String password, UserStatus status, String phone, List<RoleStatus> roles) {
        this.id = id;
        this.dni = dni;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.status = status;
        this.phone = phone;
        this.roles = roles;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}

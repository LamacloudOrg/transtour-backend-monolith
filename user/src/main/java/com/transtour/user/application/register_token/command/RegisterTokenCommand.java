package com.transtour.user.application.register_token.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class RegisterTokenCommand implements Command {

    private final String dni;
    private final String fcmToken;
    private final String device;

    public RegisterTokenCommand(String dni, String fcmToken, String device) {
        this.dni = dni;
        this.fcmToken = fcmToken;
        this.device = device;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}

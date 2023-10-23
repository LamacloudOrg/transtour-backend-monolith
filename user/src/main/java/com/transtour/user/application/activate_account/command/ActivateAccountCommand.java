package com.transtour.user.application.activate_account.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class ActivateAccountCommand implements Command {

    private final String dni;
    private final String passWord;

    public ActivateAccountCommand(String dni, String passWord) {
        this.dni = dni;
        this.passWord = passWord;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}

package com.transtour.notification.application.send_activation_code_mail.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class SendCodeCommand  implements Command {

    private final String to;
    private final String activationCode;

    public SendCodeCommand(String to, String activationCode) {
        this.to = to;
        this.activationCode = activationCode;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}

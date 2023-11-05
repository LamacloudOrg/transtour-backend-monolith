package com.transtour.notification.application.send_activation_code_mail.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class SendCodeCommand  implements Command {

    private final String to;

    public SendCodeCommand(String to) {
        this.to = to;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}

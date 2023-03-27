package com.transtour.notification.application.registration_mail.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class RegistrationMailCommand implements Command {

    private final String from;
    private final String to;
    private final String subject;

    private final String body;

    public RegistrationMailCommand(String from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}

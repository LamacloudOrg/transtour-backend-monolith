package com.transtour.notification.application.travel_notification_mail.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class TravelNotificationMailCommand implements Command {
    private final String to;
    private final String subject;

    private final String textBody;

    private final HashMap<String, String> attributes;

    public TravelNotificationMailCommand(String to, String subject, String textBody, HashMap<String, String> attributes) {
        this.to = to;
        this.subject = subject;
        this.textBody = textBody;
        this.attributes = attributes;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}

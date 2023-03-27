package com.transtour.kernel.domain.notification;

import com.transtour.kernel.domain.bus.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class NotificationEmailEvent extends DomainEvent {

    public NotificationEmailEvent() {
    }

    public NotificationEmailEvent(String aggregateId, Map<String, Serializable> attributes) {
        super(aggregateId, attributes);
    }


    public static NotificationEmailEvent create(String aggregateId,
                                                String from,
                                                String to,
                                                String subject,
                                                String body) {
        Map<String, Serializable> map = new HashMap<>();


        map.put("aggregateId", aggregateId);
        map.put("from", from);
        map.put("to", to);
        map.put("subject", subject);
        map.put("body", body);

        return new NotificationEmailEvent(aggregateId, map);

    }

    @Override
    public String eventName() {
        return "user.registration";
    }
}

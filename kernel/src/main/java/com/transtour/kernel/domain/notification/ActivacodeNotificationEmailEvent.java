package com.transtour.kernel.domain.notification;

import com.transtour.kernel.domain.bus.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ActivacodeNotificationEmailEvent extends DomainEvent {

    public ActivacodeNotificationEmailEvent() {
    }

    public ActivacodeNotificationEmailEvent(String aggregateId, Map<String, Serializable> attributes) {
        super(aggregateId, attributes);
    }


    public static ActivacodeNotificationEmailEvent create(String aggregateId,
                                                          String to,
                                                          String activationCode
    ) {
        Map<String, Serializable> map = new HashMap<>();


        map.put("aggregateId", aggregateId);
        map.put("to", to);
        map.put("activationCode", activationCode);


        return new ActivacodeNotificationEmailEvent(aggregateId, map);

    }

    @Override
    public String eventName() {
        return "user.activation.code";
    }
}

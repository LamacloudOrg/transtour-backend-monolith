package com.transtour.kernel.domain.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transtour.kernel.domain.bus.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class NotificationTravelEmailEvent extends DomainEvent {

    private final static ObjectMapper mapper = new ObjectMapper();

    public NotificationTravelEmailEvent() {
        super();
    }

    public NotificationTravelEmailEvent(String aggregateId,
                                        Map<String, Serializable> attributes) {
        super(aggregateId, attributes);
    }

    public static NotificationTravelEmailEvent create(String aggregateId,
                                                      String to,
                                                      String subject,
                                                      String body,
                                                      Map<String, String> others) throws JsonProcessingException {
        Map<String, Serializable> map = new HashMap<>();


        map.put("aggregateId", aggregateId);
        map.put("to", to);
        map.put("subject", subject);
        map.put("body", body);
        map.put("others", mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(others));

        return new NotificationTravelEmailEvent(aggregateId, map);

    }


    @Override
    public String eventName() {
        return "travel.aproved";
    }
}

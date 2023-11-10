package com.transtour.travel.domain;

import com.transtour.kernel.domain.bus.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TravelSignedEvent extends DomainEvent {

    public TravelSignedEvent() {
    }

    public TravelSignedEvent(String aggregateId, Map<String, Serializable> attributes) {
        super(aggregateId, attributes);
    }

    public static TravelSignedEvent create(
            String aggregateId,
            Long travelId

    ) {
        Map<String, Serializable> map = new HashMap<>();
        map.put("travelId", travelId);

        return new TravelSignedEvent(aggregateId, map);

    }

    @Override
    public String eventName() {
        return "travel.signed.event";
    }
}
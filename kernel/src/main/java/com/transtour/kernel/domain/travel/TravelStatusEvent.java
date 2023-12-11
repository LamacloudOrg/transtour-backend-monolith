package com.transtour.kernel.domain.travel;

import com.transtour.kernel.domain.bus.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TravelStatusEvent extends DomainEvent {

    public TravelStatusEvent() {
    }

    public TravelStatusEvent(Map<String, Serializable> attributes) {
        super(attributes);
    }

    public static TravelStatusEvent create(String travelId) {
        Map<String, Serializable> map = new HashMap<>();
        map.put("travelId", travelId);

        return new TravelStatusEvent(map);

    }

    @Override
    public String eventName() {
        return "travel.status.event";
    }
}

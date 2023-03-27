package com.transtour.kernel.domain.bus.event;

import java.io.Serializable;
import java.util.Map;

public class MalformedDomainEvent extends RuntimeException {
    public MalformedDomainEvent(String id) {
        super(String.format("Domain event is not formatted correctly. Recovered event ID: %s", id));
    }

    public MalformedDomainEvent(Map<String, Serializable> attributes) {
        super(String.format("Domain event is not formatted correctly. Recovered attributes: %s", attributes));
    }
}

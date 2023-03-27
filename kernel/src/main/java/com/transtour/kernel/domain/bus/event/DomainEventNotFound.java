package com.transtour.kernel.domain.bus.event;

public class DomainEventNotFound extends RuntimeException {
    public DomainEventNotFound(String event) {
        super(String.format("It is not a domain event. Recovered event: %s", event));
    }
}

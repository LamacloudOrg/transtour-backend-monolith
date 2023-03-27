package com.transtour.kernel.domain.bus.event;

public class NotADomainEvent extends RuntimeException {
    public NotADomainEvent(String event) {
        super(String.format("It is not a domain event. Recovered event: %s", event));
    }
}

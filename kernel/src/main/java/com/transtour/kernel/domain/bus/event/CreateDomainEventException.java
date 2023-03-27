package com.transtour.kernel.domain.bus.event;

public class CreateDomainEventException extends RuntimeException {
    public CreateDomainEventException(String name, String event) {
        super(String.format("There was an error creating the event %s. Recovered event: %s", name, event));
    }
}

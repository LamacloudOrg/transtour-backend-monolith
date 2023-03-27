package com.transtour.kernel.domain;


import com.transtour.kernel.domain.bus.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {

    private final Id aggregateId;

    private List<DomainEvent> domainEvents = new ArrayList<>();

    protected AggregateRoot(Id aggregateId) {
        this.aggregateId = aggregateId;
    }

    public final List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = domainEvents;

        domainEvents = Collections.emptyList();

        return events;
    }

    protected final void record(DomainEvent event) {
        domainEvents.add(event);
    }

    public Id getAggregateId() {
        return aggregateId;
    }
}

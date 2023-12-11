package com.transtour.kernel.domain.bus;


import com.transtour.kernel.domain.bus.event.MalformedDomainEvent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public abstract class DomainEvent {
    private String aggregateId;
    private String eventId;
    private String occurredOn;
    private Map<String, Serializable> attributes;

    public DomainEvent() {
    }

    protected DomainEvent(String aggregateId, Map<String, Serializable> attributes) {
//    ensureValidUuid(aggregateId);
//    ensureValidAttributes(attributes);
        this.aggregateId = aggregateId;
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = LocalDateTime.now().toString();
        this.attributes = attributes;
    }

    public DomainEvent(Map<String, Serializable> attributes) {
    }

    public abstract String eventName();

    private void ensureValidUuid(String value) throws IllegalArgumentException {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new MalformedDomainEvent(value);
        }
    }

    private void ensureValidAttributes(Map<String, Serializable> attributes) {

        if (attributes.isEmpty()) {
            throw new MalformedDomainEvent(attributes);
        }

        ensureValidUuid(attributes.get("aggregateId").toString());
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(String occurredOn) {
        this.occurredOn = occurredOn;
    }

    public Map<String, Serializable> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Serializable> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEvent that = (DomainEvent) o;
        return Objects.equals(aggregateId, that.aggregateId) && Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId, attributes);
    }
}

package com.transtour.kernel.infrastructure.event.kafka;


import com.transtour.kernel.domain.bus.DomainEvent;

public class KafkaException extends RuntimeException {

    public KafkaException(DomainEvent event, Throwable throwable) {
        super(String.format("There was an error publishing the event <%s>", event.getEventId()), throwable);
    }
}

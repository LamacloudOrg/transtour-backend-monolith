package com.transtour.kernel.infrastructure.event.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.transtour.kernel.domain.bus.DomainEvent;
import com.transtour.kernel.domain.bus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("kafka")
@Primary
@Component
public class KafkaEventBus implements EventBus {

    private final Logger logger = LoggerFactory.getLogger(KafkaEventBus.class);

    private final KafkaPublisher publisher;

    public KafkaEventBus(KafkaPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent domainEvent) {
        try {
            publisher.publish(domainEvent, domainEvent.eventName());
            logger.info("Message posted in Kafka");
        } catch (KafkaProducerException | JsonProcessingException e) {
            logger.info("There was an error posting the message");
            throw new KafkaException(domainEvent, e);
        }
    }
}

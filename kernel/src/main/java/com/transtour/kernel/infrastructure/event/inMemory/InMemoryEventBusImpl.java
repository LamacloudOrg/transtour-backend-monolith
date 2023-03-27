package com.transtour.kernel.infrastructure.event.inMemory;


import com.transtour.kernel.domain.bus.DomainEvent;
import com.transtour.kernel.domain.bus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("InMemory")
public class InMemoryEventBusImpl implements EventBus {

    private final Logger logger = LoggerFactory.getLogger(InMemoryEventBusImpl.class);

    List<DomainEvent> repository = new ArrayList<>();

    @Override
    public void publish(List<DomainEvent> events) {
        logger.info("Publicando mensaje");
        events.forEach(this::publish);
    }

    public void publish(DomainEvent event) {
        logger.info("Publicando mensaje");
        repository.add(event);
    }

    public List<DomainEvent> getEvents() {
        return repository;
    }
}

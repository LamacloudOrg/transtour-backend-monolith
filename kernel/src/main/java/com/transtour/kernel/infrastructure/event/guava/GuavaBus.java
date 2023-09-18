package com.transtour.kernel.infrastructure.event.guava;


import com.google.common.eventbus.AsyncEventBus;
import com.transtour.kernel.domain.bus.DomainEvent;
import com.transtour.kernel.domain.bus.EventBus;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;

@Component
@Qualifier("GuavaImpl")
@Getter
public class GuavaBus implements EventBus {

    private final com.google.common.eventbus.EventBus internalBus;

    public GuavaBus() {
        this.internalBus = new com.google.common.eventbus.EventBus();
    }

    @Override
    public void publish(List<DomainEvent> events) {
        synchronized (this){
            events.forEach(internalBus::post);
        }

    }

    public com.google.common.eventbus.EventBus getInternalBus() {
        return internalBus;
    }
}

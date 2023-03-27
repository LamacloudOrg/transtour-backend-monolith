package com.transtour.kernel.domain.bus;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
}

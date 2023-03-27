package com.transtour.kernel.domain.failover;


import com.transtour.kernel.domain.bus.DomainEvent;

public interface Failover {
    void saveEvent(final DomainEvent event);

    void saveMalformedEvent(final DomainEvent event);
}

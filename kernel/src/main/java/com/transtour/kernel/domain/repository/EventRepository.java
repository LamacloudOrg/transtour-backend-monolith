package com.transtour.kernel.domain.repository;


import com.transtour.kernel.domain.Id;
import com.transtour.kernel.domain.bus.DomainEvent;

import java.util.Optional;

public interface EventRepository {
    void save(DomainEvent event);

    void saveMalformedEvent(DomainEvent event);

    Optional<DomainEvent> getById(Id id);
}

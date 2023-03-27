package com.transtour.kernel.infrastructure.failover;

import com.transtour.kernel.domain.Service;
import com.transtour.kernel.domain.bus.DomainEvent;
import com.transtour.kernel.domain.failover.Failover;
import com.transtour.kernel.domain.repository.EventRepository;
import org.springframework.context.annotation.Profile;

@Profile("mongo")
@Service
public class MongoFailover implements Failover {

    private final EventRepository repository;

    public MongoFailover(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveEvent(DomainEvent event) {
        repository.save(event);
    }

    @Override
    public void saveMalformedEvent(DomainEvent event) {

    }
}

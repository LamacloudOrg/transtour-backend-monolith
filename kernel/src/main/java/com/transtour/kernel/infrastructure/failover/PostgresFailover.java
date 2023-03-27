package com.transtour.kernel.infrastructure.failover;


import com.transtour.kernel.domain.Service;
import com.transtour.kernel.domain.bus.DomainEvent;
import com.transtour.kernel.domain.failover.Failover;
import com.transtour.kernel.domain.repository.EventRepository;
import org.springframework.context.annotation.Profile;

@Profile("postgres")
@Service
public class PostgresFailover implements Failover {

    private final EventRepository repository;

    public PostgresFailover(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveEvent(DomainEvent event) {
        repository.save(event);
    }

    @Override
    public void saveMalformedEvent(DomainEvent event) {
        repository.saveMalformedEvent(event);
    }
}

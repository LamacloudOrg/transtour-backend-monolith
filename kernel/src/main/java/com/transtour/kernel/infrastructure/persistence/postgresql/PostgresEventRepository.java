package com.transtour.kernel.infrastructure.persistence.postgresql;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.transtour.kernel.domain.Id;
import com.transtour.kernel.domain.Service;
import com.transtour.kernel.domain.bus.DomainEvent;
import com.transtour.kernel.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

@Profile("postgres")
@Service
public class PostgresEventRepository extends PostgresFailoverRepository implements EventRepository {

    public PostgresEventRepository(JdbcTemplate jdbcTemplate, @Qualifier("customMapper") ObjectMapper mapper) {
        super(jdbcTemplate, mapper);
    }

    @Override
    public void save(DomainEvent domainEvent) {
        persistDomainEvent(domainEvent);
    }

    @Override
    public void saveMalformedEvent(DomainEvent domainEvent) {
        persistMalformedDomainEvent(domainEvent);
    }

    @Override
    public Optional<DomainEvent> getById(Id id) {
        return Optional.empty();
    }
}

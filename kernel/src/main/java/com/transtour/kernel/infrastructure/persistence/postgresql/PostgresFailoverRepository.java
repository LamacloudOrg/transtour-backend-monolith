package com.transtour.kernel.infrastructure.persistence.postgresql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.transtour.kernel.domain.bus.DomainEvent;
import com.transtour.kernel.infrastructure.jackson.ProcessDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

public abstract class PostgresFailoverRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper mapper;
    private String dddContext;

    protected PostgresFailoverRepository(JdbcTemplate jdbcTemplate, ObjectMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Autowired
    protected void setDddContext(String dddContext) {
        this.dddContext = dddContext;
    }

    public void persistDomainEvent(DomainEvent event) {
        this.persist(event, "domainevent");
    }

    public void persistMalformedDomainEvent(DomainEvent event) {
        this.persist(event, "malformedevent");
    }


    private void persist(DomainEvent event, String table) {

        try {
            jdbcTemplate.update(
                String.format(
                    "insert into %s.%s (event_id, occurred_on, event_name, aggregate_id, data) " +
                        "VALUES (?,?,?,?,?::jsonb)",
                    dddContext, table
                ),
                UUID.fromString(event.getEventId()),
                event.getOccurredOn(),
                event.eventName(),
                event.getAggregateId(),
                mapper.writeValueAsString(event));

        } catch (JsonProcessingException | DataAccessException e) {
            throw new ProcessDataException(e);
        }
    }
}

package com.transtour.kernel.infrastructure.persistence.postgresql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transtour.kernel.domain.AggregateRoot;
import com.transtour.kernel.domain.Id;
import com.transtour.kernel.domain.criteria.Criteria;
import com.transtour.kernel.domain.criteria.Filters;
import com.transtour.kernel.domain.criteria.Pageable;
import com.transtour.kernel.domain.criteria.Sentence;
import com.transtour.kernel.infrastructure.jackson.ProcessDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class PostgresRepository<T extends AggregateRoot> {

    private static final String COLUMN = "data";

    private final JdbcTemplate jdbcTemplate;
    private final Class<T> aggregateClass;
    private final ObjectMapper mapper;
    private String dddContext;

    protected PostgresRepository(JdbcTemplate jdbcTemplate, Class<T> aggregateClass, ObjectMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.aggregateClass = aggregateClass;
        this.mapper = mapper;
    }

    @Autowired
    protected void setDddContext(String dddContext) {
        this.dddContext = dddContext;
    }

    /**
     * @param t
     */
    protected void persist(T t) {

        try {

            jdbcTemplate.update(
                String.format("INSERT INTO %s.%s (aggregate_id, %s) VALUES (?,?::jsonb) " +
                    "ON CONFLICT (aggregate_id) DO UPDATE SET %s = ?::jsonb", dddContext, aggregateClass.getSimpleName().toLowerCase(), COLUMN, COLUMN),
                UUID.fromString(t.getAggregateId().getValue()),
                mapper.writeValueAsString(t),
                mapper.writeValueAsString(t));
        } catch (JsonProcessingException | DataAccessException e) {
            throw new ProcessDataException(e);
        }
    }

    /**
     * Unimplemented
     *
     * @param t
     */
    protected void update(T t) {

    }

    /**
     * Unimplemented
     *
     * @param id
     */
    protected void delete(Id id) {

    }

    /**
     * Unimplemented
     *
     * @param criteria
     */
    protected void delete(Criteria criteria) {

    }

    /**
     * Find by id.
     *
     * @param id      value
     * @param idField Column value
     * @return an optional of an aggregate
     */
    protected Optional<T> findById(Id id, String idField) {
        var sentence = getFindByIdQuery(id, idField);
        return obtainJsonFromSentenceExecution(sentence);
    }

    /**
     * Find by id.
     *
     * @param id value
     * @return an optional of an aggregate
     */
    protected Optional<T> findById(Id id) {
        return findById(id, "aggregate_id");
    }

    /**
     * Find an aggregate by searching for some id within the stored JSON.
     *
     * @param id      value
     * @param idField Column value
     * @return an optional of an aggregate
     */
    protected Optional<T> findInJSONBySomeId(Id id, String idField) {

        var sentence = new Sentence.Builder<T>()
            .select()
            .json()
            .from(aggregateClass, dddContext)
            .where(idField, id.getValue())
            .findInJson()
            .build();
        return obtainJsonFromSentenceExecution(sentence);
    }

    /**
     * Unimplemented
     *
     * @param criteria
     * @return
     */
    protected Optional<T> findByCriteria(Criteria criteria) {
        return Optional.empty();
    }

    /**
     * Unimplemented
     *
     * @param criteria
     * @return
     */
    protected List<T> searchByCriteria(Criteria criteria) {
        return Collections.emptyList();
    }

    private Optional<T> obtainJsonFromSentenceExecution(Sentence<T> sentence) {
        try {
            var json = jdbcTemplate.queryForObject(sentence.getSentence(), String.class);

            return Optional.ofNullable(mapper.readValue(json, aggregateClass));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (JsonProcessingException | DataAccessException e) {
            throw new ProcessDataException(e);
        }
    }

    /**
     * @param id
     * @return
     */
    protected Optional<String> findReadModelById(Id id) {

        var sentence = getFindByIdQuery(id, "aggregate_id");

        try {
            var json = jdbcTemplate.queryForObject(sentence.getSentence(), String.class);
            return Optional.ofNullable(json);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new ProcessDataException(e);
        }
    }

    private Sentence<T> getFindByIdQuery(Id id, String idField) {
        return new Sentence.Builder<T>()
            .select()
            .json()
            .from(aggregateClass, dddContext)
            .where(idField, id.getValue())
            .findByAggregateId()
            .build();
    }

    /**
     * Primitive method that returns all the data of a table.
     *
     * @return String list
     */
    @Deprecated
    protected List<String> findAllinJSON() {

        return jdbcTemplate.queryForList(
                String.format("select %s from %s ", COLUMN, aggregateClass.getSimpleName().toLowerCase())
            ).stream()
            .map(row -> row.get("data").toString())
            .collect(Collectors.toList());
    }

    /**
     * @param criteria
     * @return
     */
    protected List<T> searchInJsonByCriteria(Criteria criteria) {

        var sentence = new Sentence.Builder<T>()
            .select()
            .json()
            .from(aggregateClass, dddContext)
            .where(criteria)
            .findInJson()
            .orderBy()
            .build();
        try {
            return executeAndProcessSentence(sentence);
        } catch (DataAccessException e) {
            throw new ProcessDataException(e);
        }
    }

    /**
     * @param criteria
     * @return
     */
    protected Pageable<T> searchInJsonByCriteriaPageable(Criteria criteria) {

        var sentence = new Sentence.Builder<T>()
            .select()
            .json()
            .from(aggregateClass, dddContext)
            .where(criteria)
            .findInJson()
            .orderBy()
            .limitAndOffset()
            .build();
        try {
            List<T> aggregateList = executeAndProcessSentence(sentence);

            return new Pageable<>(getTotalResults(criteria), aggregateList);
        } catch (DataAccessException e) {
            throw new ProcessDataException(e);
        }
    }

    private List<T> executeAndProcessSentence(Sentence<T> sentence) {
        return jdbcTemplate.queryForList(
                sentence.getSentence()
            ).stream()
            .map(row -> {
                try {
                    return mapper.readValue(row.get(COLUMN).toString(), aggregateClass);
                } catch (JsonProcessingException e) {
                    throw new ProcessDataException(e);
                }
            })
            .collect(Collectors.toList());
    }

    /**
     * @return
     */
    protected List<T> findAll() {
        return searchInJsonByCriteria(new Criteria.Builder().build());
    }

    private Integer getTotalResults(Criteria criteria) {
        var customCriteria = new Criteria.Builder().setFilters(new Filters(criteria.getFilters())).build();
        return jdbcTemplate.queryForObject(new Sentence.Builder<T>().select().custom("count(*)").from(aggregateClass, dddContext).where(customCriteria).findInJson().build().getSentence(), Integer.class);
    }
}

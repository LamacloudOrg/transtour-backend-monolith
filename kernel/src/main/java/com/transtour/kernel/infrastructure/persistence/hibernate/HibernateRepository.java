package com.transtour.kernel.infrastructure.persistence.hibernate;


import com.transtour.kernel.domain.Id;
import org.hibernate.SessionFactory;

import java.util.Optional;

public abstract class HibernateRepository<T> {

    private final SessionFactory sessionFactory;
    private final Class<T> aggregateClass;

    public HibernateRepository(SessionFactory sessionFactory, Class<T> aggregateClass) {
        this.sessionFactory = sessionFactory;
        this.aggregateClass = aggregateClass;
    }

    protected void persist(T t) {
        sessionFactory.getCurrentSession().saveOrUpdate(t);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }

    protected Optional<T> findById(Id id, String idField) {
        return sessionFactory.getCurrentSession().byId(aggregateClass).loadOptional(id.getValue());
    }

    protected Optional<T> findById(Id id) {
        return findById(id, "aggregateId");
    }
}

package com.transtour.kernel.domain.repository;



import com.transtour.kernel.domain.AggregateRoot;
import com.transtour.kernel.domain.Id;
import com.transtour.kernel.domain.criteria.Criteria;

import java.util.Optional;

public interface CrudRepository<T extends AggregateRoot> extends SimpleRepository<T> {

    void update(T t);

    void delete(Id id);

    Optional<T> findByCriteria(Criteria criteria);
}

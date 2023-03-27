package com.transtour.kernel.domain.repository;


import com.transtour.kernel.domain.AggregateRoot;
import com.transtour.kernel.domain.criteria.Criteria;
import com.transtour.kernel.domain.criteria.Pageable;

public interface PageableRepository<T extends AggregateRoot> extends CrudRepository<T> {
    Pageable<T> searchByCriteriaPageable(Criteria criteria);
}

package com.transtour.kernel.domain.repository;


import com.transtour.kernel.domain.AggregateRoot;
import com.transtour.kernel.domain.criteria.Criteria;
import com.transtour.kernel.domain.criteria.Pageable;

public interface PageableSimpleRepository<T extends AggregateRoot> extends SimpleRepository<T> {
    Pageable<T> searchByCriteriaPageable(Criteria criteria);
}

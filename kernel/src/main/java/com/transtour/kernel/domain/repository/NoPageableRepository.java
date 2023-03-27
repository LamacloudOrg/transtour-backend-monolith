package com.transtour.kernel.domain.repository;

import com.transtour.kernel.domain.AggregateRoot;
import com.transtour.kernel.domain.criteria.Criteria;

import java.util.List;

public interface NoPageableRepository<T extends AggregateRoot> extends CrudRepository<T> {
    List<T> searchByCriteria(Criteria criteria);
}

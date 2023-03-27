package com.transtour.kernel.domain.repository;


import com.transtour.kernel.domain.AggregateRoot;

import java.util.List;

public interface ExtendedSimpleRepository<T extends AggregateRoot> extends SimpleRepository<T> {
    List<T> findAll();

}

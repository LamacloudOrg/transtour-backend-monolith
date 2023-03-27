package com.transtour.kernel.domain.repository;

import com.transtour.kernel.domain.AggregateRoot;

public interface GenericRepository<T extends AggregateRoot> extends SimpleRepository<T> {
}

package com.transtour.kernel.domain.repository;

import com.transtour.kernel.domain.AggregateRoot;
import com.transtour.kernel.domain.Id;

import java.util.Optional;

public interface SimpleRepository<T extends AggregateRoot> {

    void save(T t);

    Optional<T> getById(Id id);
}

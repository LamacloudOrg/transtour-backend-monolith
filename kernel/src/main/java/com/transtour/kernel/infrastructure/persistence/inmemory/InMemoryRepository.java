package com.transtour.kernel.infrastructure.persistence.inmemory;


import com.transtour.kernel.domain.AggregateRoot;
import com.transtour.kernel.domain.Id;
import com.transtour.kernel.domain.Service;
import com.transtour.kernel.domain.repository.GenericRepository;

import java.util.HashMap;
import java.util.Optional;

@Service
public final class InMemoryRepository<T extends AggregateRoot> implements GenericRepository<T> {

    HashMap<String, T> dataHashMap = new HashMap<>();

    @Override
    public void save(T t) {
        dataHashMap.put(t.getAggregateId().getValue(), t);
    }

    @Override
    public Optional<T> getById(Id value) {
        return Optional.ofNullable(dataHashMap.get(value.getValue()));
    }
}

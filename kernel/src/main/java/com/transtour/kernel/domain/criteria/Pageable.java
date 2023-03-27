package com.transtour.kernel.domain.criteria;


import com.transtour.kernel.domain.AggregateRoot;

import java.util.List;

public class Pageable<T extends AggregateRoot> {

    private final Integer totalResult;
    //  private final Integer totalPages; //Necesito el tamaño de la página
    private final List<T> results;

    public Pageable(Integer totalResult, List<T> results) {
        this.totalResult = totalResult;
        this.results = results;
    }

    public Integer getTotalResult() {
        return totalResult;
    }

    public List<T> getResults() {
        return results;
    }
}

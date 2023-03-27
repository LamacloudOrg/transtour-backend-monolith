package com.transtour.kernel.domain.criteria;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public final class Criteria {

    private Integer limit;
    private Integer offset;
    private String orderBy;
    private OrderType orderType;
    private Filters filters;

    public Criteria() {
    }

    /**
     * Returns a statement fragment based on the implementation of the ordering data.
     * The result is an optional to avoid nullpointers
     *
     * @return Optional<String>
     */
    public Optional<String> orderBy() {
        if (!getOrderBy().isPresent() || !getOrderType().isPresent()) {
            return Optional.empty();
        }
        return Optional.of(String.format(" ORDER BY data #> '{%s, value}' %s", orderBy, orderType.value()));
    }

    /**
     * Returns a statement fragment based on the paging data implementation.
     * The result is an optional to avoid nullpointers
     *
     * @return Optional<String>
     */
    public Optional<String> limitAndOffset() {
        if (getLimit().isEmpty()) {
            return Optional.empty();
        }

        if (getOffset().isEmpty()) {
            return Optional.of(String.format(" LIMIT %d", limit));
        }

        return Optional.of(String.format(" LIMIT %d OFFSET %d", limit, offset));
    }

    public List<Filter> getFilters() {
        if (null == filters) {
            filters = new Filters(Collections.emptyList());
        }
        return filters.getFilters();
    }

    private Optional<Integer> getLimit() {
        return Optional.ofNullable(limit);
    }

    private Optional<Integer> getOffset() {
        return Optional.ofNullable(offset);
    }

    private Optional<String> getOrderBy() {
        return Optional.ofNullable(orderBy);
    }

    private Optional<OrderType> getOrderType() {
        return Optional.ofNullable(orderType);
    }

    /**
     * Builder pattern to build a basic criteria
     */
    public static class Builder {
        private final Criteria criteria;

        public Builder() {
            criteria = new Criteria();
        }

        public LimitAndOffsetBuilder setLimit(Integer limit) {
            criteria.limit = limit;
            return new LimitAndOffsetBuilder(criteria);
        }

        public Builder setFilters(List<HashMap<String, String>> filters) {
            criteria.filters = Filters.fromValues(filters);
            return this;
        }

        public Builder setFilters(Filters filters) {
            criteria.filters = filters;
            return this;
        }

        public SortableBuilder sortable() {
            return new SortableBuilder(this.criteria);
        }

        public Criteria build() {
            return criteria;
        }
    }

    public static class LimitAndOffsetBuilder {
        private final Criteria criteria;

        public LimitAndOffsetBuilder(Criteria criteria) {
            this.criteria = criteria;
        }

        public LimitAndOffsetBuilder setOffset(Integer offset) {
            criteria.offset = offset;
            return this;
        }

        public Criteria build() {
            return criteria;
        }
    }

    /**
     * Builder pattern to build a sortable criteria
     */
    public static class SortableBuilder {
        private final Criteria criteria;

        public SortableBuilder(Criteria criteria) {
            this.criteria = criteria;
        }

        public SortableBuilderType by(String orderBy) {
            criteria.orderBy = orderBy;
            return new SortableBuilderType(this.criteria);
        }
    }

    public static class SortableBuilderType {
        private final Criteria criteria;

        public SortableBuilderType(Criteria criteria) {
            this.criteria = criteria;
        }

        public SortableBuilderAsc asc() {
            criteria.orderType = OrderType.ASC;
            return new SortableBuilderAsc(this.criteria);
        }

        public SortableBuilderDesc desc() {
            criteria.orderType = OrderType.DESC;
            return new SortableBuilderDesc(this.criteria);
        }

        public SortableBuilderOrderType setOrderType(String orderType) {
            this.criteria.orderType = OrderType.valueOf(orderType.toUpperCase());
            return new SortableBuilderOrderType(this.criteria);
        }
    }

    public static class SortableBuilderAsc {
        private final Criteria criteria;

        public SortableBuilderAsc(Criteria criteria) {
            this.criteria = criteria;
        }

        public LimitAndOffsetBuilder setLimit(Integer limit) {
            criteria.limit = limit;
            return new LimitAndOffsetBuilder(criteria);
        }

        public Criteria build() {
            return criteria;
        }
    }

    public static class SortableBuilderDesc {
        private final Criteria criteria;

        public SortableBuilderDesc(Criteria criteria) {
            this.criteria = criteria;
        }

        public LimitAndOffsetBuilder setLimit(Integer limit) {
            criteria.limit = limit;
            return new LimitAndOffsetBuilder(criteria);
        }

        public Criteria build() {
            return criteria;
        }
    }

    public static class SortableBuilderOrderType {
        private final Criteria criteria;

        public SortableBuilderOrderType(Criteria criteria) {
            this.criteria = criteria;
        }

        public LimitAndOffsetBuilder setLimit(Integer limit) {
            criteria.limit = limit;
            return new LimitAndOffsetBuilder(criteria);
        }

        public Criteria build() {
            return criteria;
        }
    }
}

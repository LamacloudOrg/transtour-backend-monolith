package com.transtour.kernel.domain.criteria;

import java.util.List;
import java.util.stream.Collectors;

public class Sentence<T> {

    private static final String JSON_COLUMN = "data";

    private String sqlSentence;
    private Class<T> aggregateClass;
    private Criteria criteria;
    private String fields;
    private String where;
    private String schema;

    private Sentence() {
    }

    public String getSentence() {
        return sqlSentence;
    }

    private String getAggregateName() {
        return aggregateClass.getSimpleName().toLowerCase();
    }

    public static class Builder<T> {

        private final Sentence<T> sentence;

        public Builder() {
            sentence = new Sentence<>();
        }

        public SelectBuilder<T> select() {
            return new SelectBuilder<>(sentence);
        }
    }

    public static class SelectBuilder<T> {
        private final Sentence<T> sentence;

        public SelectBuilder(Sentence<T> sentence) {
            this.sentence = sentence;
        }

        public JsonBuilder<T> json() {
            this.sentence.fields = JSON_COLUMN;
            return new JsonBuilder<>(sentence);
        }

        public CustomBuilder<T> custom(String value) {
            this.sentence.fields = value;
            return new CustomBuilder<>(sentence);
        }
    }

    public static class JsonBuilder<T> {
        private final Sentence<T> sentence;

        public JsonBuilder(Sentence<T> sentence) {
            this.sentence = sentence;
        }

        public FromBuilder<T> from(Class<T> aggregateClass, String schema) {
            sentence.aggregateClass = aggregateClass;
            sentence.schema = schema;
            return new FromBuilder<>(sentence);
        }
    }

    public static class CustomBuilder<T> {
        private final Sentence<T> sentence;

        public CustomBuilder(Sentence<T> sentence) {
            this.sentence = sentence;
        }

        public FromBuilder<T> from(Class<T> aggregateClass, String schema) {
            sentence.aggregateClass = aggregateClass;
            sentence.schema = schema;
            return new FromBuilder<>(sentence);
        }
    }

    public static class FromBuilder<T> {
        private final Sentence<T> sentence;

        public FromBuilder(Sentence<T> sentence) {
            this.sentence = sentence;
            this.sentence.sqlSentence = String.format("select %s from %s.%s", sentence.fields, sentence.schema, sentence.getAggregateName());
        }

        /**
         * Añade where a la consulta utilizando los filtros del criteria
         *
         * @param criteria Con toda la configuración
         * @return un WhereBuilder, una clase del patrón builder que permite configurar donde buscar
         */
        public WhereBuilder<T> where(Criteria criteria) {
            sentence.criteria = criteria;
            return new WhereBuilder<>(sentence);
        }

        public WhereBuilder<T> where(String field, String value) {

            Filter filter = Filter.create(field, FilterOperator.EQUALS.name(), value);

            sentence.criteria = new Criteria.Builder().setFilters(new Filters(List.of(filter))).build();

            return new WhereBuilder<>(sentence);
        }

        public SortableBuilder<T> orderBy(Criteria criteria) {
            sentence.criteria = criteria;
            return new SortableBuilder<>(this.sentence);
        }

        public PageableBuilder<T> limitAndOffset(Criteria criteria) {
            sentence.criteria = criteria;
            return new PageableBuilder<>(this.sentence);
        }

        public Sentence<T> build() {
            return sentence;
        }
    }

    public static class WhereBuilder<T> {
        private final Sentence<T> sentence;

        public WhereBuilder(Sentence<T> sentence) {
            this.sentence = sentence;
        }

        /**
         * Busca dentro del JSON
         */
        public FindInJsonBuilder<T> findInJson() {

            String whereFilters = sentence.criteria.getFilters().stream()
                    .map(this::createFilterSentence)
                    .collect(Collectors.joining("and"));

            if (!whereFilters.isBlank()) {
                this.sentence.sqlSentence = String.format("%s where %s", this.sentence.sqlSentence, whereFilters);
            }

            return new FindInJsonBuilder<>(this.sentence);
        }

        private String createFilterSentence(Filter filter) {
            switch (filter.operator()) {
                case BETWEEN:
                    var val1 = !filter.values().isEmpty() ? filter.values().get(0) : new FilterValue("");
                    var val2 = filter.values().size() > 1 ? filter.values().get(1) : new FilterValue("");
                    return String.format(" data #> '{%s, value}' %s %s AND %s ", filter.field().getValue(),
                            filter.operator().getValue(),
                            queryValue(filter.operator(), val1),
                            queryValue(filter.operator(), val2)
                    );
                case IN:
                    return String.format(" data #> '{%s, value}' %s ( %s ) ", filter.field().getValue(),
                            filter.operator().getValue(),
                            filter.values().stream().map(f -> queryValue(filter.operator(), f)).collect(Collectors.joining(","))
                    );
                case FULL_TEXT_SEARCH:
                    return String.format(" (data #> '{%s, value}')::text %s '%%%s%%' ", filter.field().getValue(),
                            filter.operator().getValue(),
                            filter.value().getValue());
                default:
                    return String.format(" data #> '{%s, value}' %s %s ", filter.field().getValue(),
                            filter.operator().getValue(),
                            queryValue(filter.operator(), filter.value()));
            }
        }

        //TODO: Se puede llevar a un FilterOperatorPostgres
        private String queryValue(FilterOperator operator, FilterValue value) {
            return operator.isContains() ? String.format("'%s'", value.getValue()) : String.format("'\"%s\"'", value.getValue());
        }

        /**
         * Busca por la columna AggregateId
         */
        public FindByIdBuilder<T> findByAggregateId() {

            String whereFilters = sentence.criteria.getFilters().stream()
                    .map(filter -> String.format(" %s %s '%s' ", filter.field().getValue(),
                            filter.operator().getValue(),
                            filter.value().getValue()))
                    .collect(Collectors.joining("and"));

            this.sentence.sqlSentence = String.format("%s where %s", this.sentence.sqlSentence, whereFilters);
            return new FindByIdBuilder<>(this.sentence);
        }

        public Sentence<T> build() {
            return sentence;
        }
    }

    public static class FindInJsonBuilder<T> {
        private final Sentence<T> sentence;

        public FindInJsonBuilder(Sentence<T> sentence) {
            this.sentence = sentence;
        }

        /**
         * Utiliza el criteria para ordenar los resultados
         */
        public SortableBuilder<T> orderBy() {
            return new SortableBuilder<>(this.sentence);
        }

        public PageableBuilder<T> limitAndOffset() {
            return new PageableBuilder<>(this.sentence);
        }

        public Sentence<T> build() {
            return sentence;
        }
    }

    public static class FindByIdBuilder<T> {
        private final Sentence<T> sentence;

        public FindByIdBuilder(Sentence<T> sentence) {
            this.sentence = sentence;
        }

        public Sentence<T> build() {
            return sentence;
        }
    }

    public static class SortableBuilder<T> {
        private final Sentence<T> sentence;

        public SortableBuilder(Sentence<T> sentence) {
            this.sentence = sentence;
            this.sentence.sqlSentence = String.format("%s %s", this.sentence.sqlSentence, sentence.criteria.orderBy().orElse(""));
        }

        public PageableBuilder<T> limitAndOffset() {
            return new PageableBuilder<>(this.sentence);
        }

        public Sentence<T> build() {
            return sentence;
        }
    }

    public static class PageableBuilder<T> {
        private final Sentence<T> sentence;

        public PageableBuilder(Sentence<T> sentence) {
            this.sentence = sentence;
            this.sentence.sqlSentence = String.format("%s %s", this.sentence.sqlSentence, sentence.criteria.limitAndOffset().orElse(""));
        }

        public Sentence<T> build() {
            return sentence;
        }
    }
}

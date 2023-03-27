package com.transtour.kernel.domain.criteria;

public class PostgresSentence<T> {

    private static final String JSON_COLUMN = "data";

    private String sqlSentence;
    private Class<T> aggregateClass;
    private Criteria criteria;

    private PostgresSentence() {
    }

    public String getSentence() {
        return sqlSentence;
    }

    private String getAggregateName() {
        return aggregateClass.getSimpleName().toLowerCase();
    }

    public static class Builder<T> {

        private final PostgresSentence<T> sentence;

        public Builder() {
            sentence = new PostgresSentence<>();
        }

        public SelectBuilder<T> select() {
            return new SelectBuilder<>(sentence);
        }
    }

    public static class SelectBuilder<T> {
        private final PostgresSentence<T> sentence;

        public SelectBuilder(PostgresSentence<T> sentence) {
            this.sentence = sentence;
        }

        public JsonBuilder<T> json() {
            return new JsonBuilder<>(sentence);
        }
    }

    public static class JsonBuilder<T> {
        private final PostgresSentence<T> sentence;

        public JsonBuilder(PostgresSentence<T> sentence) {
            this.sentence = sentence;
        }

        public FromBuilder<T> from(Class<T> aggregateClass) {
            sentence.aggregateClass = aggregateClass;
            return new FromBuilder<>(sentence);
        }
    }

    public static class FromBuilder<T> {
        private final PostgresSentence<T> sentence;

        public FromBuilder(PostgresSentence<T> sentence) {
            this.sentence = sentence;
            this.sentence.sqlSentence = String.format("select %s from %s", JSON_COLUMN, sentence.getAggregateName());
        }

        public WhereBuilder<T> where(Criteria criteria) {
            sentence.criteria = criteria;
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

        public PostgresSentence<T> build() {
            return sentence;
        }
    }

    public static class WhereBuilder<T> {
        private final PostgresSentence<T> sentence;

        public WhereBuilder(PostgresSentence<T> sentence) {
            this.sentence = sentence;
        }

        /**
         * Unimplemented
         */
        public void unimplemented() {
        }
    }

    public static class SortableBuilder<T> {
        private final PostgresSentence<T> sentence;

        public SortableBuilder(PostgresSentence<T> sentence) {
            this.sentence = sentence;
            this.sentence.sqlSentence = String.format("select %s from %s %s", JSON_COLUMN, sentence.getAggregateName(), sentence.criteria.orderBy().orElse(""));
        }

        public PageableBuilder<T> limitAndOffset() {
            return new PageableBuilder<>(this.sentence);
        }

        public PostgresSentence<T> build() {
            return sentence;
        }

        public static class PageableBuilder<T> {
            private final PostgresSentence<T> sentence;

            public PageableBuilder(PostgresSentence<T> sentence) {
                this.sentence = sentence;
                this.sentence.sqlSentence = String.format("select %s from %s %s %s", JSON_COLUMN, sentence.getAggregateName(), sentence.criteria.orderBy().orElse(""), sentence.criteria.limitAndOffset().orElse(""));
            }

            public PostgresSentence<T> build() {
                return sentence;
            }
        }
    }

    public static class PageableBuilder<T> {
        private final PostgresSentence<T> sentence;

        public PageableBuilder(PostgresSentence<T> sentence) {
            this.sentence = sentence;
            this.sentence.sqlSentence = String.format("select %s from %s %s", JSON_COLUMN, sentence.getAggregateName(), sentence.criteria.limitAndOffset().orElse(""));
        }

        public PostgresSentence<T> build() {
            return sentence;
        }
    }
}

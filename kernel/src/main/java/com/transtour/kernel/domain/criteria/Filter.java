package com.transtour.kernel.domain.criteria;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class Filter {
    private final FilterField field;
    private final FilterOperator operator;
    private final FilterValue value;
    private final List<FilterValue> values;

    public Filter(FilterField field, FilterOperator operator, FilterValue value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.values = List.of();
    }

    public Filter(FilterField field, FilterOperator operator, List<FilterValue> values) {
        this.field = field;
        this.operator = operator;
        this.value = null;
        this.values = values;
    }

    public static Filter create(String field, String operator, String value) {
        var filterOperator = FilterOperator.valueOf(operator.toUpperCase());
        return create(field, filterOperator, value);
    }

    public static Filter create(String field, FilterOperator operator, String value) {
        if (operator.requireSeveralValues()) {
            return new Filter(
                new FilterField(field),
                operator,
                Arrays.stream(value.split(",")).map(FilterValue::new).collect(toList())
            );
        }
        return new Filter(
            new FilterField(field),
            operator,
            new FilterValue(value)
        );
    }

    public static Filter fromValues(HashMap<String, String> values) {
        return create(values.get("field"), values.get("operator"), values.get("value"));
    }

    public FilterField field() {
        return field;
    }

    public FilterOperator operator() {
        return operator;
    }

    public FilterValue value() {
        return value;
    }

    public List<FilterValue> values() {
        return values;
    }

    public String serialize() {
        return String.format("%s.%s.%s", field.getValue(), operator.getValue(), value.getValue());
    }
}

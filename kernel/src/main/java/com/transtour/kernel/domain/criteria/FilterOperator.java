package com.transtour.kernel.domain.criteria;

public enum FilterOperator {
    EQUALS("="),
    NOT_EQUALS("!="),
    CONTAINS("?"),
    NOT_CONTAINS("NOT_CONTAINS"),
    GREATER_THAN(">"),
    GREATER_THAN_EQUALS(">="),
    LESS_THAN("<"),
    LESS_THAN_EQUALS("<="),
    IN("IN"),
    BETWEEN("BETWEEN"),
    FULL_TEXT_SEARCH("ILIKE");

    private final String operator;

    FilterOperator(String operator) {
        this.operator = operator;
    }

    public static FilterOperator fromValue(String value) {
        switch (value) {
            case "=":
                return FilterOperator.EQUALS;
            case "!=":
                return FilterOperator.NOT_EQUALS;
            case ">":
                return FilterOperator.GREATER_THAN;
            case "<":
                return FilterOperator.LESS_THAN;
            case ">=":
                return FilterOperator.GREATER_THAN_EQUALS;
            case "<=":
                return FilterOperator.LESS_THAN_EQUALS;
            case "IN":
                return FilterOperator.IN;
            case "BETWEEN":
                return FilterOperator.BETWEEN;
            case "?":
                return FilterOperator.CONTAINS;
            case "NOT_CONTAINS":
                return FilterOperator.NOT_CONTAINS;
            case "ILIKE":
                return FilterOperator.FULL_TEXT_SEARCH;
            default:
                return null;
        }
    }

    public boolean isPositive() {
        return this != NOT_EQUALS && this != NOT_CONTAINS;
    }

    public boolean isContains() {
        return this == CONTAINS;
    }

    public boolean requireSeveralValues() {
        return this == IN || this == BETWEEN;
    }

    public String getValue() {
        return operator;
    }
}

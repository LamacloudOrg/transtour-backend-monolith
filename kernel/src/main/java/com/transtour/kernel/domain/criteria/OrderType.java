package com.transtour.kernel.domain.criteria;

public enum OrderType {
    ASC("asc"),
    DESC("desc");
    private final String type;

    OrderType(String type) {
        this.type = type;
    }

    public boolean isAsc() {
        return this == ASC;
    }

    public String value() {
        return type;
    }
}

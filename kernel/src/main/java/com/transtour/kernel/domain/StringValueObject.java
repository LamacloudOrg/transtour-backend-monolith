package com.transtour.kernel.domain;

import java.util.Objects;

public abstract class StringValueObject implements ValueObject {
    private final String value;

    protected StringValueObject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringValueObject that = (StringValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

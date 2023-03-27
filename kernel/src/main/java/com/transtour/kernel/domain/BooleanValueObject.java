package com.transtour.kernel.domain;

import java.util.Objects;

public abstract class BooleanValueObject implements ValueObject {
    private final Boolean value;

    protected BooleanValueObject(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanValueObject that = (BooleanValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

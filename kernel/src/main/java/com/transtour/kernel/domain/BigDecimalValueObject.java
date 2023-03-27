package com.transtour.kernel.domain;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class BigDecimalValueObject implements ValueObject {
    private final String value;

    protected BigDecimalValueObject(String value) {
        ensureValidBigDecimal(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BigDecimalValueObject that = (BigDecimalValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    //TODO: Testear y validar
    private void ensureValidBigDecimal(String value) throws IllegalArgumentException {
        new BigDecimal(value);
    }
}

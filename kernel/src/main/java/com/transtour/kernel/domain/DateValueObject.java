package com.transtour.kernel.domain;

import java.time.ZonedDateTime;
import java.util.Objects;

public abstract class DateValueObject implements ValueObject {

    private final String value;

    protected DateValueObject(String value) {
        ensureValidZonedDateTime(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public ZonedDateTime getZonedDateTime() {
        return ZonedDateTime.parse(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateValueObject that = (DateValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private void ensureValidZonedDateTime(String value) throws IllegalArgumentException {
        ZonedDateTime.parse(value);
    }
}

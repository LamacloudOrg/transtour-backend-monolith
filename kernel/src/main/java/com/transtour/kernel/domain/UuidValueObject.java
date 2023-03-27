package com.transtour.kernel.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class UuidValueObject implements ValueObject {
    private final String value;

    protected UuidValueObject(String value) {
        ensureValidUuid(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UuidValueObject that = (UuidValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private void ensureValidUuid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
    }
}

package com.transtour.kernel.domain.bus.query;

public final class QueryHandlerExecutionError extends RuntimeException {
    public QueryHandlerExecutionError(Throwable cause) {
        super(cause);
    }

    public QueryHandlerExecutionError() {
        super();
    }
}

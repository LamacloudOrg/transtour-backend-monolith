package com.transtour.kernel.domain.bus.command;

public final class CommandHandlerExecutionError extends RuntimeException {
    public CommandHandlerExecutionError(Throwable cause) {
        super(cause);
    }

    public CommandHandlerExecutionError() {
    }
}

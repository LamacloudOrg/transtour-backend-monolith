package com.transtour.kernel.domain.bus.command;

public interface CommandHandler<C extends Command> {
    void handle(C command);
}

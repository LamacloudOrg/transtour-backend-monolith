package com.transtour.kernel.infrastructure;

import com.transtour.kernel.domain.bus.command.Command;
import com.transtour.kernel.domain.bus.command.CommandHandlerExecutionError;
import com.transtour.kernel.domain.bus.query.Query;
import com.transtour.kernel.domain.bus.query.QueryHandlerExecutionError;
import com.transtour.kernel.infrastructure.bus.IGatewayHandler;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractParentController implements IGatewayHandler {

    private final IGatewayHandler gatewayHandler;

    public AbstractParentController(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        gatewayHandler.dispatch(command);
    }

    @Override
    public void asyncDispatch(Command command) throws CommandHandlerExecutionError {
        gatewayHandler.asyncDispatch(command);
    }

    @Override
    public <R> R ask(Query query) throws QueryHandlerExecutionError {
        return gatewayHandler.ask(query);
    }

    @Override
    public <R> CompletableFuture<R> asyncAsk(Query query) throws QueryHandlerExecutionError {
        return gatewayHandler.ask(query);
    }
}

package com.transtour.kernel.infrastructure.bus;

import com.transtour.kernel.domain.bus.command.Command;
import com.transtour.kernel.domain.bus.command.CommandHandlerExecutionError;
import com.transtour.kernel.domain.bus.query.Query;
import com.transtour.kernel.domain.bus.query.QueryHandlerExecutionError;

import java.util.concurrent.CompletableFuture;

public interface IGatewayHandler  {


    void dispatch(Command command) throws CommandHandlerExecutionError;

    CompletableFuture<Void> asyncDispatch(Command command) throws CommandHandlerExecutionError;



    <R> R ask(Query query) throws QueryHandlerExecutionError;

    <R> CompletableFuture<R> asyncAsk(Query query) throws QueryHandlerExecutionError;
}

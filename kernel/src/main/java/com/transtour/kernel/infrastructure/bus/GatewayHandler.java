package com.transtour.kernel.infrastructure.bus;

import com.transtour.kernel.domain.bus.command.Command;
import com.transtour.kernel.domain.bus.command.CommandBus;
import com.transtour.kernel.domain.bus.command.CommandHandlerExecutionError;
import com.transtour.kernel.domain.bus.query.Query;
import com.transtour.kernel.domain.bus.query.QueryBus;
import com.transtour.kernel.domain.bus.query.QueryHandlerExecutionError;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
@Component
public class GatewayHandler implements IGatewayHandler {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public GatewayHandler(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        commandBus.dispatch(command);
    }

    @Override
    public void asyncDispatch(Command command) throws CommandHandlerExecutionError {
        CompletableFuture.runAsync(()->{
           try {
               commandBus.dispatch(command);
           }catch (Exception e){
               throw  new RuntimeException(e);
           }
        });
    }

    @Override
    public <R> R ask(Query query) throws QueryHandlerExecutionError {
        return queryBus.ask(query);
    }

    @Override
    public <R> CompletableFuture<R> asyncAsk(Query query) throws QueryHandlerExecutionError {
        CompletableFuture<R> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            try {
                completableFuture.complete(queryBus.ask(query));

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        });

        return completableFuture;
    }
}

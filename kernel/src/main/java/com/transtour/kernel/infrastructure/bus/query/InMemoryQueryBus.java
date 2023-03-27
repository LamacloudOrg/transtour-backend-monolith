package com.transtour.kernel.infrastructure.bus.query;

import com.transtour.kernel.domain.bus.query.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryQueryBus implements QueryBus {

    private final Map<String, QueryHandler> handlers;

    public InMemoryQueryBus(List<QueryHandler> queryHandlerImplementations) {
        this.handlers = new HashMap<>();
        queryHandlerImplementations.forEach(queryHandler -> {
            String queryClass = getQueryClassName(queryHandler);
            handlers.put(queryClass, queryHandler);
        });
    }

    @Override
    public <R> R ask(Query query) throws QueryHandlerExecutionError {
        try {

            String className = query.getClass().getTypeName();

            if (!handlers.containsKey(className)) {
                throw new QueryNotRegisteredError(query.getClass());
            }
            return (R) handlers.get(className).handle(query);
        } catch (QueryNotRegisteredError e) {
            throw new QueryHandlerExecutionError(e);
        }
    }

    private String getQueryClassName(QueryHandler handler) {

        Class<? extends QueryHandler> clazz = handler.getClass();

        Type genericInterface = clazz.getGenericInterfaces()[0];

        if (!(genericInterface instanceof ParameterizedType)) {
            throw new QueryHandlerExecutionError();
        }

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        return actualTypeArgument.getTypeName();
    }
}

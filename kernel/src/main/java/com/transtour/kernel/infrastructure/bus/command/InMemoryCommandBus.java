package com.transtour.kernel.infrastructure.bus.command;

import com.transtour.kernel.domain.bus.command.*;
import com.transtour.kernel.domain.exception.MalformedAggregate;
import com.transtour.kernel.infrastructure.event.kafka.KafkaException;
import com.transtour.kernel.infrastructure.jackson.ProcessDataException;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryCommandBus implements CommandBus {

    private final Map<String, CommandHandler> handlers;

    public InMemoryCommandBus(List<CommandHandler<? extends Command>> commandHandlerImplementations) {
        this.handlers = new HashMap<>();
        commandHandlerImplementations.forEach(commandHandler -> {
            String commandClass = getCommandClassName(commandHandler);
            handlers.put(commandClass, commandHandler);
        });

    }

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        try {

            String className = command.getClass().getTypeName();

            if (!handlers.containsKey(className)) {
                throw new CommandNotRegisteredError(command.getClass());
            }
            handlers.get(className).handle(command);
        } catch (CommandNotRegisteredError | KafkaException | ProcessDataException e) {
            throw new CommandHandlerExecutionError(e);
        } catch (IllegalArgumentException e) {
            //Esto salta si en el momento de crear el agregado falla la validación
            throw new MalformedAggregate("");
        }
    }

    private String getCommandClassName(CommandHandler handler) {
        Class<? extends CommandHandler> clazz = handler.getClass();
        Type genericInterface = clazz.getGenericInterfaces()[0];
        if (!(genericInterface instanceof ParameterizedType)) {
            throw new CommandHandlerExecutionError();
        }
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        return actualTypeArgument.getTypeName();
    }
}

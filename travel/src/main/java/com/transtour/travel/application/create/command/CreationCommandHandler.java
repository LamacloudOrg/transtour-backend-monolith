package com.transtour.travel.application.create.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.travel.application.create.CreationUC;
import org.springframework.stereotype.Service;

@Service
public class CreationCommandHandler implements CommandHandler<CreationCommand> {

    private final CreationUC useCase;

    public CreationCommandHandler(CreationUC creationUC) {
        this.useCase = creationUC;
    }

    @Override
    public void handle(CreationCommand command) {
        useCase.createTravel(command);
    }
}

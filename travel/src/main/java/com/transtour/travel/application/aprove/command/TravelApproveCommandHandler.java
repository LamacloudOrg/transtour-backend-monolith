package com.transtour.travel.application.aprove.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.travel.application.aprove.TravelApproveUC;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class TravelApproveCommandHandler implements CommandHandler<TravelApproveCommand> {

    private final TravelApproveUC useCase;

    public TravelApproveCommandHandler(TravelApproveUC useCase) {
        this.useCase = useCase;
    }

    @Override
    @SneakyThrows
    public void handle(TravelApproveCommand command) {
        useCase.approve(command);
    }
}

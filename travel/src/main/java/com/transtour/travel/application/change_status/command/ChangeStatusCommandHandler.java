package com.transtour.travel.application.change_status.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.travel.application.change_status.ChangeStatusUC;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ChangeStatusCommandHandler implements CommandHandler<ChangeStatusCommand> {

    private final ChangeStatusUC uc;

    public ChangeStatusCommandHandler(ChangeStatusUC uc) {
        this.uc = uc;
    }

    @Override
    @SneakyThrows
    public void handle(ChangeStatusCommand command) {
        uc.changeStatus(command);
    }

}

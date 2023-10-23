package com.transtour.user.application.activate_account.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.user.application.activate_account.ActivateAccountUC;
import org.springframework.stereotype.Component;

@Component
public class ActivateAccountHandler implements CommandHandler<ActivateAccountCommand> {

    private final ActivateAccountUC uc;

    public ActivateAccountHandler(ActivateAccountUC activateAccountUC) {
        this.uc = activateAccountUC;
    }

    @Override
    public void handle(ActivateAccountCommand command) {
        uc.activate(command);
    }
}

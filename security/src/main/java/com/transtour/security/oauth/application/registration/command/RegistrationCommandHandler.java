package com.transtour.security.oauth.application.registration.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.security.oauth.application.registration.RegistrationUC;
import org.springframework.stereotype.Component;

@Component
public class RegistrationCommandHandler implements CommandHandler<RegistrationCommand> {

    private final RegistrationUC useCase;

    public RegistrationCommandHandler(RegistrationUC useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(RegistrationCommand command) {
        useCase.registrate(command);
    }
}

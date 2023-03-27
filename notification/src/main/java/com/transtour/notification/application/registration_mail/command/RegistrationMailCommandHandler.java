package com.transtour.notification.application.registration_mail.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.notification.application.registration_mail.RegistrationMailUC;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMailCommandHandler implements CommandHandler<RegistrationMailCommand> {

    private final RegistrationMailUC useCase;

    public RegistrationMailCommandHandler(RegistrationMailUC useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(RegistrationMailCommand command) {
        useCase.sendMail(command);
    }
}

package com.transtour.notification.application.send_activation_code_mail.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.notification.application.send_activation_code_mail.SendCodeByMailUC;
import org.springframework.stereotype.Service;

@Service
public class SendCodeCommandHandler implements CommandHandler<SendCodeCommand> {

    private final SendCodeByMailUC useCase;

    public SendCodeCommandHandler(SendCodeByMailUC useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(SendCodeCommand command) {
        useCase.send(command);
    }
}

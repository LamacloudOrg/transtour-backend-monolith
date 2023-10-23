package com.transtour.user.application.register_token.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.user.application.register_token.RegisterTokenUC;
import org.springframework.stereotype.Component;

@Component
public class RegisterTokenCommandHandler implements CommandHandler<RegisterTokenCommand> {

    private final RegisterTokenUC registerTokenUC;

    public RegisterTokenCommandHandler(RegisterTokenUC registerTokenUC) {
        this.registerTokenUC = registerTokenUC;
    }

    @Override
    public void handle(RegisterTokenCommand command) {
        registerTokenUC.registerToken(command);
    }

}

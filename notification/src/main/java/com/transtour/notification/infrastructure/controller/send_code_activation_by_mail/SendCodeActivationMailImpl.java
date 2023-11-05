package com.transtour.notification.infrastructure.controller.send_code_activation_by_mail;

import com.transtour.kernel.infrastructure.bus.GatewayHandler;
import com.transtour.notification.application.send_activation_code_mail.command.SendCodeCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class SendCodeActivationMailImpl  implements SendCodeActivationMail{

    private final GatewayHandler gatewayHandler;

    public SendCodeActivationMailImpl(GatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity> send(RequestActivationCode requestActivationCode) {
        return gatewayHandler
                .asyncDispatch(new SendCodeCommand(requestActivationCode.getTo()))
                .thenApply(ResponseEntity::ok);

    }
}

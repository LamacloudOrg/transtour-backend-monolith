package com.transtour.user.infrastructure.controller.user;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.user.application.register_token.command.RegisterTokenCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class RegisterTokenController implements RegisterTokenControllerImpl {

    private final IGatewayHandler gatewayHandler;

    public RegisterTokenController(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> registerToken(RequestUserToken requestUserToken) {
        return gatewayHandler
                .asyncDispatch(new RegisterTokenCommand(requestUserToken.getDni(), requestUserToken.getFcmToken(), requestUserToken.getDevice()))
                .thenApply((result) -> ResponseEntity.ok(result))
                .orTimeout(5, TimeUnit.SECONDS);
    }
}

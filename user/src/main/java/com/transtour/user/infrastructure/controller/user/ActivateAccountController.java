package com.transtour.user.infrastructure.controller.user;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.user.application.activate_account.command.ActivateAccountCommand;
import com.transtour.user.shared.PasswordUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class ActivateAccountController implements ActivateAccountControllerImpl {

    private final IGatewayHandler gatewayHandler;

    public ActivateAccountController(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> activateAccount(RequestUserDni requestUserDni) {
        return gatewayHandler
                .asyncDispatch(new ActivateAccountCommand(requestUserDni.getDni(), PasswordUtil.radomGenerator()))
                .thenApply((result) -> ResponseEntity.ok(result))
                .orTimeout(5, TimeUnit.SECONDS);
    }
}

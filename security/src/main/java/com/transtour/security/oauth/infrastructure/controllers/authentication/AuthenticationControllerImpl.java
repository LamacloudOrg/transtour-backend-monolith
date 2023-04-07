package com.transtour.security.oauth.infrastructure.controllers.authentication;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.security.oauth.application.authentication.query.AunthenticationQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class AuthenticationControllerImpl implements AuthenticationController {

    private final IGatewayHandler gatewayHandler;

    public AuthenticationControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }


    @Override
    public CompletableFuture<ResponseEntity> authentication(AuthenticationRequest request) {
        return gatewayHandler
                .asyncAsk(new AunthenticationQuery(request.getDni(), request.getPassword()))
                .thenApply((result) -> ResponseEntity.ok(result));


    }

}

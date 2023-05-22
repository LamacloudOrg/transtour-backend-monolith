package com.transtour.security.oauth.infrastructure.controllers.authentication;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.security.oauth.application.authentication.query.AunthenticationQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
@RestController
public class AuthenticationControllerImpl implements AuthenticationController {

    private final IGatewayHandler gatewayHandler;

    public AuthenticationControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }


    @Override
    public CompletableFuture<ResponseEntity<Object>> authentication(AuthenticationRequest request) {
        return gatewayHandler
                .asyncAsk(new AunthenticationQuery(request.getDni(), request.getPassword()))
                .thenApply((result) -> ResponseEntity.ok(result))
                .orTimeout(5, TimeUnit.SECONDS);

    }

}

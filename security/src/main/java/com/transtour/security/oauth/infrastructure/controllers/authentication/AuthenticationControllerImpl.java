package com.transtour.security.oauth.infrastructure.controllers.authentication;

import com.transtour.kernel.infrastructure.AbstractParentController;
import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.security.oauth.application.authentication.query.AunthenticationQuery;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class AuthenticationControllerImpl extends AbstractParentController implements AuthenticationController {

    public AuthenticationControllerImpl(IGatewayHandler gatewayHandler) {
        super(gatewayHandler);
    }


    @Override
    public CompletableFuture<Object> authentication(AuthenticationRequest request) {
        return asyncAsk(new AunthenticationQuery(request.getDni(),request.getPassword()));
    }
}

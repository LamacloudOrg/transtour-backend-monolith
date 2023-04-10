package com.transtour.user.infrastructure.controller.driver;


import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.user.application.list_driver.query.ListDriversQuery;
import com.transtour.user.domain.UserType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class GetDriverController implements GetDriverControllerImpl{

    private final IGatewayHandler gatewayHandler;

    public GetDriverController(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity<Object>> findAllDriver() {
        return gatewayHandler
                .asyncAsk(new ListDriversQuery(UserType.DRIVER.getType()))
                .thenApply((result) -> ResponseEntity.ok(result))
                .orTimeout(5, TimeUnit.SECONDS);
    }
}

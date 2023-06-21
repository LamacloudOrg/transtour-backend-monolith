package com.transtour.travel.infrastructure.controllers.list;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.travel.application.list.query.ListTravelQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class TravelListControllerImpl implements ITravelListController {

    private final IGatewayHandler gatewayHandler;

    public TravelListControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity> create(Map<String, Object> params) {
        return gatewayHandler.asyncAsk(
                new ListTravelQuery(params)
        ).thenApply(ResponseEntity::ok);
    }
}

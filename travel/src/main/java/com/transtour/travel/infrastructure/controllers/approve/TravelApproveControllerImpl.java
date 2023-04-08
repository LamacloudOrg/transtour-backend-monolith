package com.transtour.travel.infrastructure.controllers.approve;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.travel.application.aprove.command.TravelApproveCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class TravelApproveControllerImpl implements TravelApproveController {

    private final IGatewayHandler gatewayHandler;

    public TravelApproveControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity> create(Long travelId) {
        return gatewayHandler
                .asyncDispatch(new TravelApproveCommand(travelId))
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(throwable -> {
                    if (throwable.getMessage().contains("400")) {
                        return ResponseEntity.badRequest().body(throwable.getCause().getMessage());
                    } else {
                        return ResponseEntity.internalServerError().body(null);
                    }
                })
                .orTimeout(5, TimeUnit.SECONDS);
    }
}

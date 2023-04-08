package com.transtour.travel.infrastructure.controllers.create;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.travel.application.create.command.CreationCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class TravelCreateControllerImpl implements TravelCreateController {

    private final IGatewayHandler gatewayHandler;

    public TravelCreateControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity> create(TravelCreateRequest request) {

        CreationCommand command = new CreationCommand(request.getStatus(), request.getDateCreated(), request.getCar(), request.getCarDriver(), request.getCarDriverName(),
                request.getTime(), request.getCompany(), request.getBc(), request.getPassengerName(), request.getPassengerEmail(), request.getReserveNumber(),
                request.getOriginAddress(), request.getDestinyAddress(), request.getObservation(), request.getAmount(), request.getWhitingTime(), request.getToll(),
                request.getParkingAmount(), request.getTaxForReturn(), request.getTotalAmount());

        return gatewayHandler
                .asyncDispatch(command)
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .orTimeout(5, TimeUnit.SECONDS);
    }
}
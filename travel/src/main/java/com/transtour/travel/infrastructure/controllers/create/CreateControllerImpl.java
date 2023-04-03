package com.transtour.travel.infrastructure.controllers.create;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.travel.application.create.command.CreationCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateControllerImpl implements CreateController {

    private final IGatewayHandler gatewayHandler;

    public CreateControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public ResponseEntity<Void> create(CreateRequest request) {

        CreationCommand command = new CreationCommand(request.getStatus(), request.getDateCreated(), request.getCar(), request.getCarDriver(), request.getCarDriverName(),
                request.getTime(), request.getCompany(), request.getBc(), request.getPassengerName(), request.getPassengerEmail(), request.getReserveNumber(),
                request.getOriginAddress(), request.getDestinyAddress(), request.getObservation(), request.getAmount(), request.getWhitingTime(), request.getToll(),
                request.getParkingAmount(), request.getTaxForReturn(), request.getTotalAmount());

        gatewayHandler.asyncDispatch(command);

        return ResponseEntity.ok(null);
    }
}
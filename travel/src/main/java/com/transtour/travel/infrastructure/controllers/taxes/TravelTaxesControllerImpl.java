package com.transtour.travel.infrastructure.controllers.taxes;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.travel.application.save_taxes.command.TravelSaveTaxesCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class TravelTaxesControllerImpl implements TravelTaxesController {

    private final IGatewayHandler gatewayHandler;

    public TravelTaxesControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity> saveTaxes(Long travelId, RequestTaxesDTO taxesDTO) {
        return gatewayHandler.asyncDispatch(new TravelSaveTaxesCommand(travelId,
                        taxesDTO.getWhitingTime(), taxesDTO.getToll()
                        , taxesDTO.getParkingAmount(), taxesDTO.getTaxForReturn()))
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .orTimeout(10, TimeUnit.SECONDS);
    }
}

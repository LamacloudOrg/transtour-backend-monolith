package com.transtour.travel.infrastructure.controllers.sign;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.travel.application.signature.command.TravelSignatureCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class TravelSignerControllerImpl implements TravelSignerController {

    private final IGatewayHandler gatewayHandler;

    public TravelSignerControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }


    @Override
    public CompletableFuture<ResponseEntity> signVoucher(Long travelId, RequestSignatureDTO signatureDTO) {
        return gatewayHandler.asyncDispatch(new TravelSignatureCommand(travelId, signatureDTO.getImage(), signatureDTO.getWidth(), signatureDTO.getHeight()))
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .orTimeout(10, TimeUnit.SECONDS);
    }
}
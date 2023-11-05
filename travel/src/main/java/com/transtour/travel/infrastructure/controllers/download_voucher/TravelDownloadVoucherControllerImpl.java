package com.transtour.travel.infrastructure.controllers.download_voucher;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.travel.application.download.command.VoucherDownloadCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class TravelDownloadVoucherControllerImpl implements TravelDownloadVoucherController {

    private final IGatewayHandler gatewayHandler;

    public TravelDownloadVoucherControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity> signVoucher(Long travelId) {
        return gatewayHandler.asyncDispatch(new VoucherDownloadCommand(travelId))
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .orTimeout(10, TimeUnit.SECONDS);
    }
}

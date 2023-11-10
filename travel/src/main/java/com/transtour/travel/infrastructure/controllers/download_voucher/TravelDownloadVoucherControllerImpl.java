package com.transtour.travel.infrastructure.controllers.download_voucher;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.travel.application.read_voucher.VoucherResponse;
import com.transtour.travel.application.read_voucher.query.ReadVoucherQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
public class TravelDownloadVoucherControllerImpl implements TravelDownloadVoucherController {

    private final IGatewayHandler gatewayHandler;

    public TravelDownloadVoucherControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity<Object>> download(Long travelId) throws InterruptedException, ExecutionException, TimeoutException {

        return gatewayHandler.asyncAsk(new ReadVoucherQuery(travelId))
                .thenApply(o ->
                        ResponseEntity.ok()
                                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + ((VoucherResponse) o).getPdfName())
                                // Content-Type
                                .contentType(MediaType.APPLICATION_PDF)
                                // Contet-Length
                                .contentLength(((VoucherResponse) o).getPdf().length)
                                .body(((VoucherResponse) o).getPdf())
                );
    }
}

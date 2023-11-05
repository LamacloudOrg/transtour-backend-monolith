package com.transtour.travel.infrastructure.controllers.download_voucher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/travel/voucher")
public interface TravelDownloadVoucherController {

    @PatchMapping("/downloadPdf/{id}")
    CompletableFuture<ResponseEntity> signVoucher(@PathVariable Long travelId);
}


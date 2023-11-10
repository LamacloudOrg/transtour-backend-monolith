package com.transtour.travel.infrastructure.controllers.download_voucher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RequestMapping("/api/v1/travel")
public interface TravelDownloadVoucherController {

    @GetMapping("/{id}/download")
    CompletableFuture<ResponseEntity<Object>> download(@PathVariable("id") Long travelId) throws InterruptedException, ExecutionException, TimeoutException;
}


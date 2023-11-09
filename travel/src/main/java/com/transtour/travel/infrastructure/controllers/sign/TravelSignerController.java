package com.transtour.travel.infrastructure.controllers.sign;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/travel")
public interface TravelSignerController {

    @PatchMapping("/{travelId}/signature")
    CompletableFuture<ResponseEntity> signVoucher(@PathVariable Long travelId, @Validated @RequestBody RequestSignatureDTO signatureDTO);
}

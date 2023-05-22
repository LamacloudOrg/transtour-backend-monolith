package com.transtour.travel.infrastructure.controllers.approve;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/travel")
public interface TravelApproveController {

    @PatchMapping("/{id}/approve")
    CompletableFuture<ResponseEntity> create(
            @PathVariable("id") Long travelId
    );


}

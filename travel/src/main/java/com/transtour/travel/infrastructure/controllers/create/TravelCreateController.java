package com.transtour.travel.infrastructure.controllers.create;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/travel")
public interface TravelCreateController {

    @PostMapping()
    CompletableFuture<ResponseEntity> create(
            @RequestBody TravelCreateRequest request, @AuthenticationPrincipal UserDetails userDetails
    );
}

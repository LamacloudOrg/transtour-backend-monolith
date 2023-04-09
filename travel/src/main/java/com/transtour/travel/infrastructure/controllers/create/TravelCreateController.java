package com.transtour.travel.infrastructure.controllers.create;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/travel")
@CrossOrigin("*")
public interface TravelCreateController {

    @PostMapping()
    CompletableFuture<ResponseEntity> create(
            @RequestBody TravelCreateRequest request
    );
}
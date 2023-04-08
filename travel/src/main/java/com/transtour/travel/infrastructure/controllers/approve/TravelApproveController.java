package com.transtour.travel.infrastructure.controllers.approve;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/travel")
@CrossOrigin("*")
public interface TravelApproveController {

    @PatchMapping("/{id}/approve")
    CompletableFuture<ResponseEntity> create(
            @PathVariable("id") Long travelId
    );


}

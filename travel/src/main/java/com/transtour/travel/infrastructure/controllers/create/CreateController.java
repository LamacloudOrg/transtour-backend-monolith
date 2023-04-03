package com.transtour.travel.infrastructure.controllers.create;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/travel")
@CrossOrigin("*")
public interface CreateController {

    @PostMapping()
    public ResponseEntity<Void> create(
            @RequestBody CreateRequest request
    );
}

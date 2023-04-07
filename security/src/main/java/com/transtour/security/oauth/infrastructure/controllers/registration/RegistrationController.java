package com.transtour.security.oauth.infrastructure.controllers.registration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/oauth")
@CrossOrigin("*")
public interface RegistrationController {
    @PostMapping("/register")
    public CompletableFuture<ResponseEntity> register(
            @RequestBody RegisterRequest request
    );
}

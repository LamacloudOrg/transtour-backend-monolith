package com.transtour.security.oauth.infrastructure.controllers.authentication;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/oauth")
@CrossOrigin("*")
public interface AuthenticationController {

    @PostMapping("/authenticate")
    public CompletableFuture<Object> authentication(
            @RequestBody AuthenticationRequest request
    );
}

package com.transtour.security.oauth.infrastructure.controllers.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/oauth")
public interface AuthenticationController {

    @PostMapping("/authenticate")
    CompletableFuture<ResponseEntity<Object>> authentication(
            @RequestBody AuthenticationRequest request
    );
}

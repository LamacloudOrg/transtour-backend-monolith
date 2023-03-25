package com.transtour.security.oauth.controllers;

import com.transtour.security.oauth.dtos.AuthenticationRequest;
import com.transtour.security.oauth.dtos.AuthenticationResponse;
import com.transtour.security.oauth.dtos.RegisterRequest;
import com.transtour.security.oauth.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/oauth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}

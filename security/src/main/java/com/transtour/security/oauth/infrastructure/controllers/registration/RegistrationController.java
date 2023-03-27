package com.transtour.security.oauth.infrastructure.controllers.registration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/oauth")
@CrossOrigin("*")
public interface RegistrationController {
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody RegisterRequest request
    );
}

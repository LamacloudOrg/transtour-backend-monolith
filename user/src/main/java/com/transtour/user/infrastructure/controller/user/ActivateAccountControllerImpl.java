package com.transtour.user.infrastructure.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/oauth")
public interface ActivateAccountControllerImpl {

    @PostMapping("/activateAccount")
    CompletableFuture<ResponseEntity<Void>> activateAccount(@RequestBody RequestUserDni requestUserDni);

}

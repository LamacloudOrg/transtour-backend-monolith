package com.transtour.user.infrastructure.controller.user;

import org.springframework.http.ResponseEntity;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/notification")
public interface RegisterTokenControllerImpl {

    @PostMapping("/registerToken")
    CompletableFuture<ResponseEntity<Void>> registerToken(@RequestBody RequestUserToken requestUserToken);

}

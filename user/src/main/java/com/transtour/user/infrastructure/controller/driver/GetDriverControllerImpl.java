package com.transtour.user.infrastructure.controller.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/user")
public interface GetDriverControllerImpl {

    @GetMapping("/driver")
    CompletableFuture<ResponseEntity<Object>> findAllDriver();
}

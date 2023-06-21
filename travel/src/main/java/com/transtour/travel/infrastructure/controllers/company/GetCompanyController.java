package com.transtour.travel.infrastructure.controllers.company;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/company")
public interface GetCompanyController {

    @GetMapping
    CompletableFuture<ResponseEntity> findAll();

}

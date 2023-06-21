package com.transtour.travel.infrastructure.controllers.list;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/travel")
public interface ITravelListController {

    @GetMapping
    CompletableFuture<ResponseEntity> create(
            @RequestParam Map<String, Object> params
    );
}

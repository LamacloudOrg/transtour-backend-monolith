package com.transtour.user.infrastructure.controller.driver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/user/driver")
@CrossOrigin("*")
public interface GetDriverControllerImpl {

    @GetMapping()
    CompletableFuture<ResponseEntity<Object>> findAllDriver();
}

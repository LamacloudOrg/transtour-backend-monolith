package com.transtour.backend.controller;

import com.transtour.backend.dto.RegisterRequest;
import com.transtour.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<RegisterRequest> findUser(@RequestParam("dni") Long dni) {
        return service.find(dni.toString());
    }


}
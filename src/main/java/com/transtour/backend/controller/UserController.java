package com.transtour.backend.controller;

import com.transtour.backend.dto.RegisterDTO;
import com.transtour.backend.dto.UserDTO;
import com.transtour.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/oauth/token")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<String> login(@RequestBody RegisterDTO user) {
        return service.generateToken(user);
    }

    @PostMapping("/oauth/refresh")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public String refreshToken(@RequestBody UserDTO user) {
        return "por implementar";
    }

    //TODO gregar este metodo.
    /*
    @PutMapping("/activateAccount")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<Void> reactivate(@RequestBody Long dni) {
        return service.reactivate(dni);
    }
    */

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<UserDTO> findUser(@RequestParam("dni") Long dni) {
        return service.find(dni);
    }

    @PostMapping("/update/password")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<String> userRegister(@RequestBody RegisterDTO user) {
        return service.register(user);
    }

}
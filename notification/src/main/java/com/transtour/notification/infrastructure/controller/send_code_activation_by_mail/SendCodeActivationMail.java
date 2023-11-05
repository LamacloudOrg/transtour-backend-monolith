package com.transtour.notification.infrastructure.controller.send_code_activation_by_mail;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/oauth")
public interface SendCodeActivationMail {
    @PostMapping("/activation-code")
    CompletableFuture<ResponseEntity> send(@RequestBody RequestActivationCode requestActivationCode);
}

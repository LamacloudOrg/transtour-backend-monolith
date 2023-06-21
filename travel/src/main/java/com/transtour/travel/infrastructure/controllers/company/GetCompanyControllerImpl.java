package com.transtour.travel.infrastructure.controllers.company;

import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.travel.application.list_company.query.ListCompanyQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class GetCompanyControllerImpl implements GetCompanyController {

    private final IGatewayHandler gatewayHandler;

    public GetCompanyControllerImpl(IGatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }

    @Override
    public CompletableFuture<ResponseEntity> findAll() {
        return gatewayHandler
                .asyncAsk(new ListCompanyQuery())
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .orTimeout(5, TimeUnit.SECONDS);
    }
}

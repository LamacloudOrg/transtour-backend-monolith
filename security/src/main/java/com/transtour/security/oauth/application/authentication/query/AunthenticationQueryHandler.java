package com.transtour.security.oauth.application.authentication.query;

import com.transtour.kernel.domain.Service;
import com.transtour.kernel.domain.bus.query.Query;
import com.transtour.kernel.domain.bus.query.QueryHandler;
import com.transtour.security.oauth.application.AuthenticationResponse;
import com.transtour.security.oauth.application.authentication.AuthenticationUC;

import java.util.concurrent.CompletableFuture;

@Service
public class AunthenticationQueryHandler implements QueryHandler<AunthenticationQuery, AuthenticationResponse> {

    private final AuthenticationUC useCase;

    public AunthenticationQueryHandler(AuthenticationUC useCase) {
        this.useCase = useCase;
    }

    @Override
    public AuthenticationResponse handle(AunthenticationQuery query) {
        return useCase.authenticate(query);
    }
}


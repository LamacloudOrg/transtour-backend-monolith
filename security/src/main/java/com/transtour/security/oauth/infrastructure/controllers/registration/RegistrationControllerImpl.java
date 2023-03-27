package com.transtour.security.oauth.infrastructure.controllers.registration;

import com.transtour.kernel.infrastructure.AbstractParentController;
import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.security.oauth.application.registration.command.RegistrationCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationControllerImpl extends AbstractParentController implements RegistrationController {


    public RegistrationControllerImpl(IGatewayHandler gatewayHandler) {
        super(gatewayHandler);
    }

    @Override
    public ResponseEntity<Void> register(RegisterRequest request) {
        RegistrationCommand command = new RegistrationCommand(request.getId(), request.getDni(), request.getEmail(), request.getFullName(), request.getPassword(), request.getStatus(), request.getPhone(), request.getRoles());

        asyncDispatch(command);

        return ResponseEntity.ok(null);
    }
}

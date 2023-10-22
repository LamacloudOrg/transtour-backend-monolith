package com.transtour.user.application.register_token;

import com.transtour.kernel.exceptions.DriverNotExists;
import com.transtour.user.application.register_token.command.RegisterTokenCommand;
import com.transtour.user.domain.Driver;
import com.transtour.user.infrastructure.persistence.jpa.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterTokenUC {
    private final DriverRepository driverRepository;

    public RegisterTokenUC(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void registerToken(RegisterTokenCommand command) {

        Optional<Driver> optionalDriver = driverRepository.findByDni(command.getId().toString());
        optionalDriver.orElseThrow(DriverNotExists::new);

        Driver driver = optionalDriver.get();
        driver.setFirebaseToken(command.getFcmToken());
        driver.setType_device(command.getDevice());
        driverRepository.save(driver);
    }
}
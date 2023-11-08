package com.transtour.user.application.register_token;

import com.transtour.kernel.exceptions.DriverNotExists;
import com.transtour.user.application.register_token.command.RegisterTokenCommand;
import com.transtour.user.domain.User;
import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegisterTokenUC {
    private final UserRepository userRepository;

    public RegisterTokenUC(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerToken(RegisterTokenCommand command) {

        User user = userRepository.findByDni(command.getDni()).orElseThrow(DriverNotExists::new);

        user.getTokenDriver().setFirebaseToken(command.getFcmToken());
        user.getTokenDriver().setDeviceType(command.getDevice());
        userRepository.save(user);
    }
}
package com.transtour.user.application.activate_account;

import com.transtour.kernel.exceptions.DriverNotExists;
import com.transtour.kernel.exceptions.UserNotExists;
import com.transtour.user.application.activate_account.command.ActivateAccountCommand;
import com.transtour.user.domain.Driver;
import com.transtour.user.domain.User;
import com.transtour.user.domain.UserStatus;
import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivateAccountUC {

    private final UserRepository userRepository;

    public ActivateAccountUC(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void activate(ActivateAccountCommand command) {

        Optional<User> optionalUser = userRepository.findByDni(command.getDni());
        optionalUser.orElseThrow(UserNotExists::new);

        User user = optionalUser.get();
        user.setStatus(UserStatus.ENABLED);
        user.setPassword(command.getPassWord());
        userRepository.save(user);
    }
}

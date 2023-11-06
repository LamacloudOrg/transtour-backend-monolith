package com.transtour.user.application.activate_account;

import com.transtour.kernel.domain.bus.EventBus;
import com.transtour.kernel.domain.notification.ActivacodeNotificationEmailEvent;
import com.transtour.kernel.exceptions.UserNotExists;
import com.transtour.user.application.activate_account.command.ActivateAccountCommand;
import com.transtour.user.domain.User;
import com.transtour.user.domain.UserStatus;
import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivateAccountUC {

    private final UserRepository userRepository;
    private final EventBus eventBus;

    private final PasswordEncoder bCrypt;

    public ActivateAccountUC(UserRepository userRepository, @Qualifier("GuavaImpl") EventBus eventBus, BCryptPasswordEncoder bCrypt) {
        this.userRepository = userRepository;
        this.eventBus = eventBus;
        this.bCrypt = bCrypt;
    }

    public void activate(ActivateAccountCommand command) {

        User user = userRepository.findByDni(command.getDni()).orElseThrow(UserNotExists::new);
        user.setStatus(UserStatus.ENABLED);
        user.setPassword(bCrypt.encode(command.getPassWord()));
        userRepository.save(user);

        eventBus.publish(List.of(
                ActivacodeNotificationEmailEvent.create(
                        UUID.randomUUID().toString(),
                        user.getEmail(),
                        command.getPassWord()
                )
        ));

    }
}

package com.transtour.user.application.activate_account;

import com.transtour.kernel.domain.bus.EventBus;
import com.transtour.kernel.domain.notification.ActivacodeNotificationEmailEvent;
import com.transtour.kernel.exceptions.UserNotExists;
import com.transtour.user.application.activate_account.command.ActivateAccountCommand;
import com.transtour.user.domain.User;
import com.transtour.user.domain.UserStatus;
import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivateAccountUC {

    private final UserRepository userRepository;
    private final EventBus eventBus;

    public ActivateAccountUC(UserRepository userRepository, EventBus eventBus) {
        this.userRepository = userRepository;
        this.eventBus = eventBus;
    }

    public void activate(ActivateAccountCommand command) {

        User user = userRepository.findByDni(command.getDni()).orElseThrow(UserNotExists::new);
        user.setStatus(UserStatus.ENABLED);
        user.setPassword(command.getPassWord());
        userRepository.save(user);

        eventBus.publish(List.of(
                ActivacodeNotificationEmailEvent.create(
                        UUID.randomUUID().toString(),
                        user.getEmail()
                )
        ));

    }
}

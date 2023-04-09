package com.transtour.security.oauth.application.registration;

import com.transtour.kernel.domain.bus.EventBus;
import com.transtour.kernel.domain.notification.NotificationEmailEvent;
import com.transtour.security.oauth.application.registration.command.RegistrationCommand;
import com.transtour.user.domain.User;
import com.transtour.user.domain.UserStatus;
import com.transtour.user.persistence.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RegistrationUC {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final EventBus eventBus;

    public RegistrationUC(UserRepository repository, PasswordEncoder passwordEncoder, @Qualifier("GuavaImpl") EventBus eventBus) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.eventBus = eventBus;
    }

    public void registrate(RegistrationCommand command) {

        User user = User.builder()
                .id(command.getId())
                .fullName(command.getFullName())
                .email(command.getEmail())
                .phone(command.getPhone())
                .dni(command.getDni())
                .status(UserStatus.ENABLED)
                .password(passwordEncoder.encode(command.getPassword()))
                .build();
        repository.save(user);
        NotificationEmailEvent event = NotificationEmailEvent.create(UUID.randomUUID().toString(),
                "pomalianni@gmail.com",
                user.getEmail(),
                "UserCreation",
                "Su usuario fue registrado correctamente");
        eventBus.publish(List.of(event));
    }
}

package com.transtour.security.oauth.application.registration;

import com.transtour.kernel.domain.Service;
import com.transtour.kernel.domain.user.User;
import com.transtour.kernel.repository.UserRepository;
import com.transtour.security.oauth.application.registration.command.RegistrationCommand;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class RegistrationUC {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationUC(UserRepository repository,PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrate(RegistrationCommand command) {

        User user = User.builder()
                .id(command.getId())
                .fullName(command.getFullName())
                .email(command.getEmail())
                .phone(command.getPhone())
                .dni(command.getDni())
                .status(command.getStatus())
                //.roles(Set.copyOf(command.getRoles()))
                .password(passwordEncoder.encode(command.getPassword()))
                .build();
        repository.save(user);
    }
}

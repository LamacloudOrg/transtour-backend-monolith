package com.transtour.backend.services;

import com.transtour.backend.dto.ActivationAccountDTO;
import com.transtour.backend.dto.RegisterDTO;
import com.transtour.backend.dto.UserDTO;
import com.transtour.backend.exception.InactiveUser;
import com.transtour.backend.model.User;
import com.transtour.backend.repository.UserRepository;
import com.transtour.backend.utils.PasswordUtil;
import com.transtour.backend.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.github.dozermapper.core.Mapper;
import com.transtour.backend.exception.UserNotExists;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    @Qualifier("userRepo")
    UserRepository repository;

    @Autowired
    private Mapper mapper;

    public CompletableFuture<String> generateToken(RegisterDTO userDTO) {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    Optional<User> optionalUser = repository.findByDniAndPassword(userDTO.getDni(), userDTO.getPassword());

                    optionalUser.orElseThrow(UserNotExists::new);
                    User user = optionalUser.get();
                    if (!user.getStatus().equals("ENABLED")) throw new InactiveUser();
                    return TokenUtil.createJWT("1", null, user.getDni().toString(), user.getRole(), 2000L);
                }
        );

        return completableFuture;
    }

    public CompletableFuture<UserDTO> find(Long dni) {

        CompletableFuture<UserDTO> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    Optional<User> optionalUser = repository.findByDni(dni);
                    optionalUser.orElseThrow(UserNotExists::new);
                    User user = optionalUser.get();
                    UserDTO userAccountDTO = new UserDTO();
                    mapper.map(user, userAccountDTO);
                    return userAccountDTO;
                }
        );

        return completableFuture;
    }


    public CompletableFuture<String> activate(Long dni,String randomPassword) {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    Optional<User> userOpt = repository.findByDni(dni);
                    userOpt.orElseThrow(UserNotExists::new);
                    User user = userOpt.get();
                    user.setStatus("ENABLED");
                    user.setPassword(randomPassword);
                    repository.save(user);
                    return randomPassword;
                }
        );

        return completableFuture;
    }

    /*
    public CompletableFuture<Void> sendCodeByEmail(ActivationAccountDTO activationAccountDTO){
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(
                ()-> this.serviceNotication.sendActivationCode(activationAccountDTO)
        );
        return completableFuture;
    }

    public CompletableFuture<Void> reactivate(Long dni) {
        CompletableFuture activate = this.activate(dni, PasswordUtil.radomGenerator());
        activate.thenAccept(code -> this.sendCodeByEmail(new ActivationAccountDTO(dni,(String) code)));
        return activate;
    }
    */

    public CompletableFuture<String> register(RegisterDTO user) {
        return this.activate(user.getDni(), user.getPassword());
    }
}

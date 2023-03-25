package com.transtour.security.oauth.services;


import com.transtour.kernel.exceptions.UserNotExists;
import com.transtour.kernel.model.Token;
import com.transtour.kernel.model.TokenType;
import com.transtour.kernel.model.User;
import com.transtour.kernel.repository.TokenRepository;
import com.transtour.kernel.repository.UserRepository;
import com.transtour.security.oauth.dtos.AuthenticationRequest;
import com.transtour.security.oauth.dtos.AuthenticationResponse;
import com.transtour.security.oauth.dtos.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .id(request.getId())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .dni(request.getDni())
                .status(request.getStatus())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        User savedUser = repository.saveAndFlush(user);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getDni(),
                        request.getPassword()
                )
        );
        User user = repository.findByDni(request.getDni())
                .orElseThrow(() -> {
                    return new UserNotExists();
                });
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.saveAndFlush(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

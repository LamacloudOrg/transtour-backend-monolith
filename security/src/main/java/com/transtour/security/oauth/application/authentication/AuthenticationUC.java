package com.transtour.security.oauth.application.authentication;

import com.transtour.kernel.domain.Service;
import com.transtour.kernel.domain.bus.command.Command;
import com.transtour.kernel.domain.user.Token;
import com.transtour.kernel.domain.user.TokenType;
import com.transtour.kernel.domain.user.User;
import com.transtour.kernel.exceptions.UserNotExists;
import com.transtour.kernel.repository.TokenRepository;
import com.transtour.kernel.repository.UserRepository;
import com.transtour.security.oauth.application.AuthenticationResponse;
import com.transtour.security.oauth.application.authentication.query.AunthenticationQuery;
import com.transtour.security.oauth.configuration.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class AuthenticationUC {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    public AuthenticationUC(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService, TokenRepository tokenRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
    }

    public AuthenticationResponse authenticate(AunthenticationQuery query) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        query.getDni(),
                        query.getPassword()
                )
        );
        User user = userRepository.findByDni(query.getDni())
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
        tokenRepository.save(token);
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

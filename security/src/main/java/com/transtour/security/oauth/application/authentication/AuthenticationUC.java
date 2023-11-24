package com.transtour.security.oauth.application.authentication;

import com.transtour.kernel.exceptions.UserNotExists;
import com.transtour.security.oauth.application.AuthenticationResponse;
import com.transtour.security.oauth.application.authentication.query.AunthenticationQuery;
import com.transtour.security.oauth.configuration.JwtService;
import com.transtour.user.domain.SessionTokens;
import com.transtour.user.domain.TokenType;
import com.transtour.user.domain.User;
import com.transtour.user.infrastructure.persistence.jpa.FirebaseTokenRepository;
import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AuthenticationUC {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final FirebaseTokenRepository firebaseTokenRepository;

    public AuthenticationUC(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService, FirebaseTokenRepository firebaseTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.firebaseTokenRepository = firebaseTokenRepository;
    }

    public AuthenticationResponse authenticate(AunthenticationQuery query) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        query.getDni(),
                        query.getPassword()
                )
        );
        User user = userRepository.findByDni(query.getDni())
                .orElseThrow(UserNotExists::new);
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    private void saveUserToken(User user, String jwtToken) {
        SessionTokens sessionTokens = SessionTokens.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        firebaseTokenRepository.save(sessionTokens);
    }

    private void revokeAllUserTokens(User user) {
        List<SessionTokens> validUserSessionTokens = firebaseTokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserSessionTokens.isEmpty())
            return;
        validUserSessionTokens.forEach(sessionTokens -> {
            sessionTokens.setExpired(true);
            sessionTokens.setRevoked(true);
        });
        firebaseTokenRepository.saveAll(validUserSessionTokens);
    }
}

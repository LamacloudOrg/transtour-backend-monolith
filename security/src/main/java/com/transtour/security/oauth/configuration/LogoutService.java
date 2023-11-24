package com.transtour.security.oauth.configuration;


import com.transtour.user.domain.SessionTokens;
import com.transtour.user.infrastructure.persistence.jpa.FirebaseTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final FirebaseTokenRepository firebaseTokenRepository;


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        SessionTokens storedSessionTokens = firebaseTokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedSessionTokens != null) {
            storedSessionTokens.setExpired(true);
            storedSessionTokens.setRevoked(true);
            firebaseTokenRepository.saveAndFlush(storedSessionTokens);
            SecurityContextHolder.clearContext();
        }
    }
}
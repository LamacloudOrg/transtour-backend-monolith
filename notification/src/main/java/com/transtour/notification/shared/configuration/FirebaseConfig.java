package com.transtour.notification.shared.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    FirebaseApp firebaseApp(GoogleCredentials credentials) {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    GoogleCredentials googleCredentials() {

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("firebase-service-account.json")) {
            if (is == null) {
                throw new NullPointerException();
            }
            return GoogleCredentials.fromStream(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

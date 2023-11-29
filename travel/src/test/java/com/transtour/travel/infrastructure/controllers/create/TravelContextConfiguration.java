package com.transtour.travel.infrastructure.controllers.create;

import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.mockito.Mockito.spy;

@TestConfiguration
public class TravelContextConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public JavaMailSenderImpl mailSender() {
        return new JavaMailSenderImpl();
    }

    @Bean
    public UserRepository userRepositoryBean(){
        return spy(UserRepository.class);
    }


}

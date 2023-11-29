package com.transtour.backend;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@TestConfiguration
public class ContextConfiguration {


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public JavaMailSenderImpl mailSender() {
        return new JavaMailSenderImpl();
    }
}

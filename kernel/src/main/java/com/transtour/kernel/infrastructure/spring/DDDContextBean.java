package com.transtour.kernel.infrastructure.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DDDContextBean {

    @Value("${ddd.context}")
    private String context;

    @Bean
    public String dddContext() {
        return context;
    }
}

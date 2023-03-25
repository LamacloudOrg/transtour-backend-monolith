package com.transtour.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.transtour.kernel.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(com.transtour.kernel.Application.class, args);
    }

}
package com.transtour.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.transtour.security.oauth", "com.transtour.kernel"})
@EntityScan(value = "com.transtour.kernel.domain.user")
@EnableJpaRepositories(basePackages = "com.transtour.kernel.shared.infrastructure.persistence.userrepository")
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}

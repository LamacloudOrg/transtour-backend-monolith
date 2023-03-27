package com.transtour.backend;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.transtour.kernel", "com.transtour.security.oauth", " com.transtour.notification"})
@EntityScan(value = "com.transtour.kernel.domain.user")
@EnableJpaRepositories(basePackages = "com.transtour.kernel.shared.infrastructure.persistence.userrepository")
public class BackendApplication implements ApplicationRunner {


    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}

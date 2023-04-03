package com.transtour.backend;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.transtour.kernel", "com.transtour.security.oauth", "com.transtour.notification", "com.transtour.travel"})
@EntityScan(basePackages = {"com.transtour.kernel.domain.user", "com.transtour.travel.domain"})
@EnableJpaRepositories(basePackages = {"com.transtour.kernel.shared.infrastructure.persistence.userrepository", "com.transtour.travel.infrastructure.persistence"})
public class BackendApplication implements ApplicationRunner {

    private static final long MEGABYTE = 1024L * 1024L;


    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();

        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                +

                bytesToMegabytes(memory));
    }

    private long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
}

package com.transtour.travel.infrastructure.controllers.create;

import com.transtour.kernel.infrastructure.bus.GatewayHandler;
import com.transtour.kernel.infrastructure.bus.command.InMemoryCommandBus;
import com.transtour.kernel.infrastructure.bus.query.InMemoryQueryBus;
import com.transtour.kernel.infrastructure.event.guava.GuavaBus;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("global,mail,auditing,log-prod,tc")
@ExtendWith(SpringExtension.class)
@Import({TestSecurityConfig.class, AuiditinConfig.class, TravelContextConfiguration.class, InMemoryCommandBus.class, InMemoryQueryBus.class, GuavaBus.class, GatewayHandler.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class TravelCreateControllerImplIntegrationTest {

    @LocalServerPort
    private Integer port;


    @Container
    protected static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>()
            .withInitScript("db/init.sql")
            .withDatabaseName("postgresql")
            .withPassword("!Postgr@s321!")
            .withUsername("userDB")
            .withReuse(true);


    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        postgresqlContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgresqlContainer.stop();
    }



    static String getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }


    @Autowired
    @Qualifier("JpaRepository")
    TravelRepository travelRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        travelRepository.deleteAll();
    }

    @Test
    @WithUserDetails("manager@email.com")
    void given_a_valid_customer_should_return_200_and_customer_should_exist() throws IOException {

        given()
                .contentType(ContentType.JSON)
                .body(getResourceFileAsString("travelcreation.json"))
                .when()
                .post("/api/v1/travel")
                .then()
                .statusCode(200);

        Travel travel = travelRepository.findById(1L).orElseThrow(RuntimeException::new);
        assertEquals("AZN 323",travel.getPayload().getCar());
        assertEquals(1,travel.getOrderNumber());
    }

}

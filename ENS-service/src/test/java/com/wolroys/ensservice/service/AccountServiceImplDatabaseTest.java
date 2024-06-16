package com.wolroys.ensservice.service;


import com.wolroys.ensservice.entity.AccountDto;
import com.wolroys.ensservice.entity.AccountRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountServiceImplDatabaseTest {

    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );
    @LocalServerPort
    private Integer port;
    @Autowired
    private AccountService accountService;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }

    @AfterAll
    static void tearDown() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    @Order(1)
    void databaseShouldSaveAccount() {
        System.out.println(accountService.getAllAccounts());

        AccountRequest request = new AccountRequest();
        request.setEmail("test@mr.ru");
        request.setPassword("qwerty123");

        AccountDto result = accountService.createAccount(request);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getEmail()).isEqualTo("test@mr.ru");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/accounts")
                .then()
                .statusCode(200)
                .body(".", hasSize(1));
    }


    @Test
    @Order(2)
    void entityShouldBeUpdatedInDB() {
        AccountRequest createRequest = new AccountRequest();
        createRequest.setEmail("updated@mail.ru");
        createRequest.setId(1L);

        accountService.createAccount(createRequest);

        AccountRequest request = new AccountRequest();
        request.setEmail("updated@mail.ru");
        request.setId(1L);

        AccountDto result = accountService.updateAccount(request);

        assertThat(result).isNotNull();

        AccountDto account = accountService.getById(1L);

        assertThat(account.getEmail()).isEqualTo("updated@mail.ru");
    }

}

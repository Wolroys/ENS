package com.wolroys.ensservice.service;


import com.wolroys.ensservice.entity.AccountDto;
import com.wolroys.ensservice.entity.AccountRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Testcontainers
@SpringBootTest
class AccountServiceImplDatabaseTest {

    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @Autowired
    private AccountService accountService;

    @BeforeAll
    static void setUp() {
        postgres.start();
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }

    @AfterAll
    static void tearDown() {
        postgres.stop();
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

        AccountDto account = accountService.getById(1L);

        assertThat(account).isNotNull();
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

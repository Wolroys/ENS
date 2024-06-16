package com.wolroys.ensservice.service;

import com.wolroys.ensservice.entity.Account;
import com.wolroys.ensservice.entity.AccountDto;
import com.wolroys.ensservice.repository.AccountRepository;
import com.wolroys.ensservice.util.AccountMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void shouldReturn1Element() {
        Account account = new Account();
        account.setEmail("test@mail.ru");
        account.setId(1L);

        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setEmail(account.getEmail());

        List<Account> accounts = List.of(account);

        when(accountRepository.findAll()).thenReturn(accounts);
        when(accountMapper.toDto(any(Account.class))).thenReturn(accountDto);

        List<AccountDto> allAccounts = accountService.getAllAccounts();

        assertThat(allAccounts).hasSize(1);
    }
}
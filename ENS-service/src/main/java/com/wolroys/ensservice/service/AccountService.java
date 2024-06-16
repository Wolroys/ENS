package com.wolroys.ensservice.service;


import com.wolroys.ensservice.entity.AccountDto;
import com.wolroys.ensservice.entity.AccountRequest;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAllAccounts();

    AccountDto getById(Long id);

    AccountDto createAccount(AccountRequest request);

    AccountDto updateAccount(AccountRequest request);

    AccountDto deleteAccount(Long id);
}

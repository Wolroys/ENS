package com.wolroys.ensservice.service;

import com.wolroys.ensservice.entity.Account;
import com.wolroys.ensservice.entity.AccountDto;
import com.wolroys.ensservice.entity.AccountRequest;
import com.wolroys.ensservice.repository.AccountRepository;
import com.wolroys.ensservice.util.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @Override
    public AccountDto getById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public AccountDto createAccount(AccountRequest request) {
        Account account = new Account();

        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());

        accountRepository.save(account);

        return accountMapper.toDto(account);
    }

    @Transactional
    public AccountDto updateAccount(AccountRequest request) {
        Account currAccount = accountRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        if (StringUtils.hasText(request.getEmail())) {
            currAccount.setEmail(request.getEmail());
        }

        if (StringUtils.hasText(request.getPassword())) {
            currAccount.setPassword(request.getPassword());
        }

        return accountMapper.toDto(currAccount);
    }

    @Transactional
    public AccountDto deleteAccount(Long id) {
        Account currAccount = accountRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        accountRepository.delete(currAccount);

        return accountMapper.toDto(currAccount);
    }
}
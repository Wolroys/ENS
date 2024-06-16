package com.wolroys.ensservice.controller;

import com.wolroys.ensservice.entity.AccountDto;
import com.wolroys.ensservice.entity.AccountRequest;
import com.wolroys.ensservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @PutMapping("/edit")
    public ResponseEntity<AccountDto> update(@RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.updateAccount(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }
}

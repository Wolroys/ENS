package com.wolroys.ensservice.service;

import com.wolroys.ensservice.entity.Account;
import com.wolroys.ensservice.message.KafkaSender;
import com.wolroys.ensservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final KafkaSender kafkaSender;
    private final AccountRepository accountRepository;

    @Override
    public void sendMessage(String email) {
        Optional<Account> account = accountRepository.findByEmail(email);
        account.ifPresent(value -> kafkaSender.sendMessage("mail-topic", value));
    }
}

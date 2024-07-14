package com.wolroys.ensservice.message;

import com.wolroys.ensservice.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, Account> kafkaTemplate;

    public void sendMessage(String topicName, Account account) {
        log.info("Sending account info to account: {}", account.getEmail());
        kafkaTemplate.send(topicName, account);
    }
}

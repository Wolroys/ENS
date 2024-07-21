package com.wolroys.ensservice.message;


import com.wolroys.ensservice.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageConsumer {


    @KafkaListener(topics = "mail-topic", containerFactory = "kafkaListenerContainerFactory")
    public void sendMail(Account account){
        try {
            log.info("Account - {} received", account.getEmail());
            System.out.println(account);
        } catch (Exception e) {
            log.error("Error with receiving account - {}", account.getEmail());
        }
    }
}

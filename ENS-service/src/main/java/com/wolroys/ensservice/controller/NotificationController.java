package com.wolroys.ensservice.controller;

import com.wolroys.ensservice.entity.AccountRequest;
import com.wolroys.ensservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public void sendNotificationToEmail(@RequestBody AccountRequest request) {
        notificationService.sendMessage(request.getEmail());
    }
}

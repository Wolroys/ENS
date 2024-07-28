package com.wolroys.ensservice.controller;

import com.wolroys.ensservice.util.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public ResponseEntity<Message> helloAdmin() {
        return new ResponseEntity<>(new Message(true, "Hello, admin"),
                HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}

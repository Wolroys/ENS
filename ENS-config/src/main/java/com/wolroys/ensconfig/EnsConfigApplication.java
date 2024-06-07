package com.wolroys.ensconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EnsConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsConfigApplication.class, args);
    }

}

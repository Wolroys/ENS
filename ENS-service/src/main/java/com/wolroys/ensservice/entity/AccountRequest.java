package com.wolroys.ensservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    private Long id;

    private String email;

    private String password;
}

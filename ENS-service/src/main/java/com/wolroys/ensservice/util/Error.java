package com.wolroys.ensservice.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Error {

    private String message;
    private int code;

    public Error(String message, int code) {
        this.message = message;
        this.code = code;
    }
}

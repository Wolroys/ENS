package com.wolroys.ensservice.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {

    private boolean status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Error error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public Message(boolean status, Error error) {
        this.status = status;
        this.error = error;
    }

    public Message(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}

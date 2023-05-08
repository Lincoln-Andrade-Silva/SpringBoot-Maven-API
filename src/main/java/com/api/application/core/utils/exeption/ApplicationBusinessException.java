package com.api.application.core.utils.exeption;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Setter
@Getter
public class ApplicationBusinessException extends Exception {
    @Serial
    private static final long serialVersionUID = 2594220962674232227L;
    private String title;
    private String input;
    private String message;
    private int httpStatusCode;

    public ApplicationBusinessException(String code) {
        super(code);
    }

    public ApplicationBusinessException(String title, String message) {
        super(title);
        this.setMessage(message);
    }

    public ApplicationBusinessException(String title, String message, String input) {
        super(title);
        this.setTitle(title);
        this.setMessage(message);
        this.setInput(input);
    }

    public ApplicationBusinessException(int httpStatusCode, String title, String message, String input) {
        super(title);
        this.title = title;
        this.message = message;
        this.setInput(input);
        this.httpStatusCode = httpStatusCode;
    }

    public ApplicationBusinessException(int httpStatusCode, String title, String message) {
        super(title);
        this.title = title;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
package com.api.application.core.utils.core.responses;

import com.api.application.core.utils.exeption.ApplicationBusinessException;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {

    @Serial
    private static final long serialVersionUID = 8927944169026414109L;
    private String input;
    private String message;

    public Response() {
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setResponse(ApplicationBusinessException e) {

        if (e.getMessage() != null) {
            this.message = e.getMessage();
        }

        if (e.getInput() != null) {
            this.input = e.getInput();
        }

    }
}

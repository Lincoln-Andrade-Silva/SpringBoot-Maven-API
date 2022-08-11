package com.api.application.utils.core.responses;

import com.api.application.utils.exeption.ApplicationBusinessException;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {

    @Serial
    private static final long serialVersionUID = 8927944169026414109L;
    private String severity;
    private String message;
    private String input;
    private String version;

    public Response() {
    }

    public String getSeverity() {
        return this.severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
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

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setResponse(ApplicationBusinessException e) {
        if (e.getSeverity() != null) {
            this.severity = e.getSeverity().toString();
        }

        if (e.getMessage() != null) {
            this.message = e.getMessage();
        }

        if (e.getInput() != null) {
            this.input = e.getInput();
        }

    }

    public long length() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream out = new ObjectOutputStream(baos);
            out.writeObject(this);
            out.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return (long)(baos.toByteArray().length / 1024);
    }
}

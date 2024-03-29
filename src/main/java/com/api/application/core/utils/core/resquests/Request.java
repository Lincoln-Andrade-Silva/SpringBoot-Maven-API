package com.api.application.core.utils.core.resquests;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 5475411755679503065L;
    private String locale;

    public Request() {
    }

    public Request(String locale) {
        this.locale = locale;
    }

    public Request(String locale, String authorization) {
        this.locale = locale;
    }
}

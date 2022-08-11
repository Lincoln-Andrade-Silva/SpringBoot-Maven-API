package com.api.application.utils.core.resquests;

import java.io.Serializable;

public class Request implements Serializable {
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

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}

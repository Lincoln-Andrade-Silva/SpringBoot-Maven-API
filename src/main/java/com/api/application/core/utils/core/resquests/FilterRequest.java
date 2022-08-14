package com.api.application.core.utils.core.resquests;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class FilterRequest<T> extends Request {
    private static final long serialVersionUID = 4754856071150007342L;
    private String strSearch;
    private T data;

    public FilterRequest(String strSearch, String locale) {
        if (strSearch != null) {
            try {
                this.strSearch = URLDecoder.decode(strSearch, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
            }
        } else {
            this.strSearch = "";
        }

        this.setLocale(locale);
    }

    public FilterRequest(String strSearch, String locale, String authorization) {
        if (strSearch != null) {
            try {
                this.strSearch = URLDecoder.decode(strSearch, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException var5) {
                var5.printStackTrace();
            }
        } else {
            this.strSearch = "";
        }

        this.setLocale(locale);
        //this.setAuthorization(authorization);
    }

    public String getStrSearch() {
        return this.strSearch;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
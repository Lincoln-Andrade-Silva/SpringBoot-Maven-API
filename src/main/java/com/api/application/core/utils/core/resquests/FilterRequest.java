package com.api.application.core.utils.core.resquests;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Data
@EqualsAndHashCode(callSuper = true)
public class FilterRequest<T> extends Request {
    @Serial
    private static final long serialVersionUID = 4754856071150007342L;
    private String strSearch;
    private T data;

    public FilterRequest(String strSearch) {
        if (strSearch != null) {
            this.strSearch = URLDecoder.decode(strSearch, StandardCharsets.UTF_8);
        } else {
            this.strSearch = "";
        }
    }

    public FilterRequest(String strSearch, String locale) {
        if (strSearch != null) {
            this.strSearch = URLDecoder.decode(strSearch, StandardCharsets.UTF_8);
        } else {
            this.strSearch = "";
        }

        this.setLocale(locale);
    }
}
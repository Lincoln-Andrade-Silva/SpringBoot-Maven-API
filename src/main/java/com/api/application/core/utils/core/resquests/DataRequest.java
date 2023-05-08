package com.api.application.core.utils.core.resquests;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public class DataRequest<T> extends Request {
    @Serial
    private static final long serialVersionUID = -9199226445117528353L;
    private T data;

    public DataRequest(T object) {
        this.data = object;
    }

    public DataRequest(T object, String locale) {
        this.data = object;
        this.setLocale(locale);
    }

    public DataRequest(T object, String locale, String authorization) {
        this.data = object;
        this.setLocale(locale);
    }
}
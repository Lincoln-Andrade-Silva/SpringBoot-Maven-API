package com.api.application.utils.core.resquests;

public class DataRequest<T> extends Request {
    private static final long serialVersionUID = -9199226445117528353L;
    private T data;

    public DataRequest() {
    }

    public DataRequest(T object, String locale) {
        this.data = object;
        this.setLocale(locale);
    }

    public DataRequest(T object, String locale, String authorization) {
        this.data = object;
        this.setLocale(locale);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
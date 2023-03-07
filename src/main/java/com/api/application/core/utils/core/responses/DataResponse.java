package com.api.application.core.utils.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> extends Response {

    @Serial
    private static final long serialVersionUID = 5605829304634L;
    private T data;

    public DataResponse() {
    }

    public DataResponse(String version) {
        this.setVersion(version);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

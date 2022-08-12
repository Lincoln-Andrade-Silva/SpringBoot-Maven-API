package com.api.application.utils.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataListResponse<T> extends Response {
    private static final long serialVersionUID = -9024022837229919768L;

    private List<T> data;
    private Integer totalPages = 0;
    private Long totalData = 0L;

    public DataListResponse() {}

    public DataListResponse(String version) {
        this.setVersion(version);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalData() {
        return totalData;
    }

    public void setTotalData(Long totalData) {
        this.totalData = totalData;
    }
}
package com.api.application.core.utils.core.resquests;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public class PaginationRequest implements Serializable {
    private static final long serialVersionUID = 6869286734342943565L;
    private Pageable page;
    private String sortByAttribute;
    private Boolean ascendingOrder;

    public PaginationRequest(Integer page, Integer pageSize, String sortByAttribute, Boolean ascendingOrder) {
        if (page != null && pageSize != null) {
            this.page = PageRequest.of(page, pageSize);
        } else {
            this.page = PageRequest.of(0, 2147483647);
        }

        this.sortByAttribute = sortByAttribute;
        this.ascendingOrder = ascendingOrder == null ? Boolean.TRUE : ascendingOrder;
    }

    public Pageable getPage() {
        return this.page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

    public String getSortByAttribute() {
        return this.sortByAttribute;
    }

    public void setSortByAttribute(String sortByAttribute) {
        this.sortByAttribute = sortByAttribute;
    }

    public Boolean getAscendingOrder() {
        return this.ascendingOrder;
    }

    public void setAscendingOrder(Boolean ascendingOrder) {
        this.ascendingOrder = ascendingOrder;
    }
}

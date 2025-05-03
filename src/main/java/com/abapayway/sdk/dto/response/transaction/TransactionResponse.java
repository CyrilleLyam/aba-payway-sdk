package com.abapayway.sdk.dto.response.transaction;

import java.util.List;

import com.abapayway.sdk.dto.response.Status;

public class TransactionResponse {

    private List<Transaction> data;
    private String page;
    private String pagination;
    private Status status;

    // Getters and Setters
    public List<Transaction> getData() {
        return data;
    }

    public void setData(List<Transaction> data) {
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


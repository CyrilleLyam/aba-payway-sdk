package com.abapayway.sdk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class CheckTransactionRequest {
    @JsonProperty("req_time")
    @NotBlank(message = "Request time is required")
    private String reqTime;

    @JsonProperty("tran_id")
    @NotBlank(message = "Transaction ID is required")
    private String tranId;

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }
}

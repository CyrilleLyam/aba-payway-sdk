package com.abapayway.sdk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class CheckTransactionRequest {
    @JsonProperty("tran_id")
    @NotBlank(message = "Transaction ID is required")
    private String tranId;

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }
}
